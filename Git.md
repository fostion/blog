### Git使用

[参考信息](http://www.jianshu.com/p/b357df6794e3)

[文档](http://git.oschina.net/progit/)

#### 分支概念
分支是我们所说的项目平行概念，一个主分支是共同使用的，一个是个人分支用来编写自己负责功能的代码，由于写一个功能需要一段时间，若是未完成提交那么会影响他人的，所以最好自己有一个分支，完成后就合并到主分支
注意：分支有两种，一种是本地创建，开发者可以使用本地创建分支，将需要添加的功能加到分支里面去，然后合并分支，最后删除。另一种项目网页添加分支，主要功能是一个是master（上线版）,一个是dev（开发板）

创建本地分支一般流程：
```
git checkout -b dev 创建并切换到dev分支

等价于

git branch dev　　创建dev分支
git checkout dev　切换到dev分支
```

```
git branch 查看分支
```

```
添加和提交
git add file
git commit -m "detail"
```

```
合并分支
git checkout master 切回到主分支
git merge dev　主分支合并dev分支
```

```
合并完成后删除dev分支
git branch -d dev
git branch -D dev 强制删除(可以没有合并)
git branch 查看分支情况
git push 提交代码
```

##### 若是合并的时候出现错误
```
1. git status 查看错误内容
2. 打开文件修改冲突后提交
```

#### 暂时使用架构：
git中一共有 master和dev分支，其中master为上线分支，dev为开发分支，实际上我们开发并不会直接改变dev分支，每个人需要自行的创建个人分支，然后提交自己的分支到服务器，直到完成一个功能点后，需要

##### 创建分支
```
git checkout -b devName #创建devName分支
git push origin devName #推送到远程devName分支（若是没有这个分支就可以创建一个分支(远程)）
```

##### 获取远程分支并关联起来
```powershell
git branch -r #查看远程分支
git branch -v #查看各个分支最后一次提交
git checkout -b dev origin/dev #本地创建dev分支关联到服务器dev分支，注意：先创建然后关联，若是直接打 git checkout origin/dev就会出现错误，同时必须关联后才能够获取远程分支最新代码

#有时候本地创建dev分支后，即使提交到远程也是无法pull实际原因是因为没有设置upstream上流，所以需要下面设置
git branch --set-upstream-to=origin/dev4 dev4
```

#### 合并不同分支
```
git status #查看文件状态，判断文件是否有改变和添加
git diff fileName #查看文件不同，修改文件的冲突
```

#### 取消提交
```powershell
#查看版本号
git log

#文件还没有操作 放弃文件改动
git checkout file

#若是文件已经add 放弃添加操作
git reset HEAD file

#只回退commit信息,若需要commit,不需要add
git reset --soft <版本号>

#回退到某个版本保留代码
git reset --mixed <版本号>

#回退到某个版本且使用该版本代码
git reset --hard <版本号>
```

#### 从远程拉项目
```
#从远程拉取项目不合并
git fetch

#从远程拉取项目合并
git pull
```

#### 为保持原的干净
```
#从远程拉去项目到本地(代码没有合并)
1.git fetch

#当前分支合并fetch拉到本地的dev分支
2.git rebase origin/dev

3.处理冲突

#继续合并
4.git rebase --countain

#删除没有用的提交可以用rebase -i或者reset
5.git reset 版本号（git log查看以前版本）

＃提交和强制推送到远程自己的分支
6.git push -f origin 分支名称
```


#### Git Subtree使用
公司开发过程中难免有一些公用的库文件，这些库文件也是由git进行管理，那么现在有这样的使用场景：项目PA需要使用LA,LB,LC库文件，但是一旦LA,LB,LC修改了，项目PA需要立即更换代码或者是不同分支，这里就需要使用到git subtree命令了。

#### submodule和subtree区别
git submodule
- 仓库clone下来需要init和update
- 产生.gitmodule类似文件
- git submodule删除起来比较费劲

git subtree
- 避免以上问题
- 管理和更新流程比较方便
- git v1.5.2以后建议使用git subtree

#### 引入库
```git
//为待引入仓库添加别名
git remote add -f lib-module1(仓库别名) 项目路径/项目url

//添加外部仓库为子目录
git subtree add -P module1(引入项目别名) lib-module1（仓库别名） master(引入项目分支)
```

#### 引入库修改和更新
```
//在项目中修改引入库
git subtree push -P module1(引入项目别名) lib-module1(仓库别名) master(引入项目分支)

//更新库最新代码
//这里有问题若是引入的本地文件，只有本地文件更新才会更新，若是远程文件，只有远程文件更新才会更新
git subtree pull -P module1(引入项目别名) lib-module1(仓库别名) master(引入项目分支)


//将库从项目中抽离出来
git subtree split -P module1 -b lib-module1(将module1库抽出来成为一个叫lib-module1的branch)

cd ..
mkdir lib-module1
cd lib-module1
git init
git pull ../TestProject lib-module1(从TestProject中把lib-module1库复制出来)

//若是要清理原来资料库内容
//这种方式虽然可以清除库，但是会rewrite commits history
git filter-branch -f --index-filter "git rm -r -f -q --cached --ignore-unmatch module1" --prune-empty HEAD

```

#### ssh生成
```
1. 进入.ssh文件夹
cd ~/.ssh/   //若是没有该文件夹，则自行创建 mkdir ~/.ssh

2. 配置参数
git config --global user.name "userName"
git config --global user.email "userEmail@xx.com"

3. 生成key
ssh-keygen -t rsa -C "userEmail@xx.com"

4. 连续按3次回车跳过设置密码

5. 进入.ssh文件夹，用记事本打开id_rsa.pub，复制里面内容

6. 将ssh key 添加到账号key下，终端环境下使用时选择yes
```

#### ignore
```
.gradle
.idea
/local.properties
/.idea/workspace.xml
/.idea/libraries
.DS_Store
/build
/captures
*.iml
*.apk
```

注意：有时候修改ignore无效，这是因为.gitignore只能忽略那些原来没有被track的文件，如果某些文件已经被纳入了版本管理中，则修改.gitignore是无效的，因此,需要执行以下命令

```
git rm -r --cached .  
git add .
```

