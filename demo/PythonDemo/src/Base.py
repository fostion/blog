#!/usr/bin/env python3
# -*- coding: utf-8 -*-

from collections import Iterable
from collections import Iterator

# 学习网站:
# http://www.liaoxuefeng.com/wiki/0014316089557264a6b348958f449949df42a6d3a2e542c000/001431664106267f12e9bef7ee14cf6a8776a479bdec9b9000

# hello world
print("hello world")

# 强大的输出
print("200 - 100 =", 200 - 100)

# 每遇到一个 , 输出为空格
print("i", "am", "king", "of", "the", "world")

# 输入
# print("输入内容为:",input("请输入你想显示的内容:"))

print(1024, "*", 768, "=", 1024 * 768)

# 代码没有中括号通过缩进代表代码块 冒号: 代表里面的内容
a = 10
if a > 5:
    print(a)
else:
    print(-a)

# 1.23 * 10的8次方
print(1.23e8)

# 字符可以使用 ''/"" 来表示
print('i am ok')
print("i am ok")
print("i \'m ok")

# r'content' content内容不转义
print(r'\\\\t\\\\')

# 换行字符串  等价于 123 \n 456 \n 789
print('''123
456
789''')

# 布尔值 and or not值
print(True and True)
print(True or False)
print(False or False)
print(not True)

# 空值None代表Null
print(0 == None)

# 变量不需要申明,类型由 = 右边决定
a = 123
print(a)
a = "123abc"
print(a)

# 引用例子,变量间 = ,只是引用到内存地址,不是变量
a = 'abc'
b = a
a = 'xyz'
print("a =", a, "b=", b)

# 常量 实际上python没有常量,只有变量,但是若是大写一般不改变内容
PI = 3.14159265359

# 运算符 / 精确到小数点一位  //没有小数点只有整形
print(9 / 2)
print(9 // 2)

# 编码问题 ASCII,UTF-8,Unicode
print('包含中文的str')
print(ord('A'))  # A在内存中Unicode表示
print(ord("中"))
print(chr(65))  # 65编码在在内存中对应着的字符
print(chr(20013))

# b'content' 代表content的byte[]内容
print('ABC'.encode('ascii'))
print('中文'.encode('utf-8'))

# 计算字符长度
print(len('abcdefghijklmn'))

# 字符串格式化
print("%%我是字符串%s,我是整数%d,我是浮点数%s,我是十六进制%x" % ("这是字符串", 10, 10.23, 0x16))

# 集合
# list []
l = ['joy', 'tom', 'alla']
print('原数据:', l)

# 插入chen
l.insert(0, "chen")
print('添加chen:', l)

# 删除chen
l.pop(0)
print('删除chen', list)

# list里面的数据可以不同
l = ['str', 123, 456.123, hex(123)]
print("list里面不同数据:", l)

# 添加其他list
l1 = [1, 2, 3, 4, 5, 6]
l2 = ['a', 'b', 'c', 'd']
print("插入l2前:", l1)
print("插入l2前长度:", len(l1))
l1.insert(0, l2)
print("插入l2后", l1)
print("插入l2后长度:", len(l1))

# tuple () 元祖,不可变得list,申明时必须确认
tuple = ('1', 2, hex(3))
print("tuple", tuple)

# 当tuple为1位时,必须定义成为(1,)
tuple = (1,)
print("tuple:", tuple)
tuple = (1)
print("数字tuple:", tuple)

# tuple只是保证以及不改变,内部可以改变
l1 = ['a', 'b']
tuple = (l1, 1, 2, 3)
print("l1修改前tuple", tuple)
l1.append('c')
print("l1修改后tuple", tuple)

# dict 内置字典 可以作为json数据,但是key是不可变对象只能是字符串和整形若是list就会出错
# dict 和 list区别
# 1.查找和插入速度快,不会随着key的增加而变慢
# 2.需要占用大量内存
# 3.list相反
dict = {'john': 95, 'bob': 75, 'alin': 60}
print(dict)
print(dict['bob'])

# 复杂类型
dict = {'john': {'name': 'john', 'age': 18, 'sex': 'male'},
        'bob': {'name': 'bob', 'age': 25, 'sex': 'female'}}
print("复杂字典:", dict['bob'])

# 判断是否存在key 或者通过get方法获取
print("bob1是否在字典里", "bob1" in dict)
print("bob1是否在字典里", dict.get("bob1"))

# set与dict类似,一组key集合,但是不存储value值,key值不能重复 无序/无重复的元素组合
s = set([1, 1, 2, 2, 3])
print("set内容:", s)

# 不可变对象 a只是变量 'abc'是对象,实质上'abc'并没有被改变
a = "abc"
print("将数据A替换a:", a.replace("a", "A"))
print("原数据:", a)

# 测试tuple
tuple = (1, 2, 3)
dict = {1: 1, 2: 2, 3: 3, tuple: 456}
print("包含tuple的dict", dict)
s = set([1, 2, 3, tuple])
print("包含tuple的set", s)


# 定义函数
# 导入函数 from fileName import functionName
def my_function(x):
    # 参数检查
    if not (isinstance(x, (int, float))):
        raise TypeError('bad input type')
    return x + 10;


print(my_function(10.1))
# print(my_function("123"))

# 不操作 使用 pass 类似java continus
a = 10
if a > 5:
    pass


# 函数返回duogezhi
def fun_more_pa(x, y, z):
    return x + 10, y + 20, z + 30


x, y, z = fun_more_pa(10, 10, 10)
print("多函数返回tuple:", fun_more_pa(10, 10, 10))
print('x:%d,y:%d,z%d' % (x, y, z))


# 函数的默认参数
def power(x, n=2):
    s = 1
    while n > 0:
        n = n - 1
        s = s * x
    return s


print("2的2次方:", power(2))
print("2的3次方:", power(2, 3))


# 使用默认参数不按顺序
def stu_msg(name, sex, city="广州", age=18):
    print("个人信息:", name, sex, city, age)


stu_msg('张三', '男')
stu_msg('李四', '女', age=14)
stu_msg('王五', '女', '北京')


# 不定参数
def fun_undefind(*number):
    all = 0
    for n in number:
        all = all + n
    print("sum值:", all)


fun_undefind(1, 2, 3, 4)

# list和tuple类型可以使用*来传递
tuple = (1, 2, 3)
fun_undefind(*tuple)


# 关键参数 **kw代表关键参数,自动形成数据字典 **代表将key-value传入
# 区别*kw 代表 tuple  **kw代表dict
def person(name, sex, **kw):
    print('name:', name, 'sex:', sex, 'other:', kw)


person('张三', '男')
person('李四', '女', city='深圳', age=14)
person('王五', '女', city='北京', age=20)

# 高级属性特性
# list或tuple的切片功能
l = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print("切片 0-3", l[0:3])
print("所有数每5个取一个", l[::5])
print("前6个数每3个取一个", l[:6:3])

# 迭代
# python中迭代不局限与list和tuple,还包含dict,对象
# 同时迭代可以去除内容不限制数量
for char in 'abcd':
    print('迭代abcd:', char)

dict = {'a': 1, 'b': 2, 'c': 3}
for key in dict:
    print("dict 迭代key:", key)

for value in dict.values():
    print("dict 迭代value:", value)

for key, value in dict.items():
    print("dict 迭代item:%s - %d" % (key, value))

for item in [(1, 2), (3, 4), (5, 6)]:
    print("list内容:", item)

# 判断一个对象是否迭代
print('字符串123是否可迭代:', isinstance('123', Iterable))
print('整数123是否可迭代:', isinstance(123, Iterable))

# 列表生成式
# List Comprehensions
# 生成一个list [1-10]
l = list(range(1, 11))
print('List comprehensions 内容: ', l)

# 生成一个list[1*1,2*2,3*3...]
l = [x * x for x in range(1, 11)]
print('l 里面内容: ', l)

# 加入判断条件
l = [x * x for x in range(1, 11) if x % 2 == 0]
print('l 加入判断条件里面内容: ', l)

# 多层循环
l = [m + n for m in "abc" for n in '123456']
print('l 多层循环里面内容: ', l)

# 生成器
# 作用通过列表生成式可以直接创建列表,但是受到内存大小限制
# 列表长度肯定有限制,若是列表元素可以按照某个算法推算出来
# 这样意味着不需要创建完全list,取出部分然后显示出来,处理完
# 后,取出下一组处理,循环的结果就是生成器

# 方法一:将[]改成()
l = [x * x for x in range(10)]
print('列表:', l)
g = (x * x for x in range(10))
print('生成器:', g)
for item in g:
    print('取出生成器里面的内容:', item)


# next 和 yield 使用,yield代表断电,从上次执行代码,继续执行
# odd相当于一个生成器,使用next到达底部仍然执行会报错,要使用for语句
def odd():
    print('step 1')
    yield 1
    print('step 2')
    yield 2
    print('step 3')
    yield 3


# next执行yield
o = odd()
print(next(o))
print(next(o))

for item in o:
    print('使用for语句:', item)


def triangles(line):
    l = [1]
    yield l
    l = [1, 1]
    yield l
    n = 2
    while n <= line:
        l1 = [1]
        for i in range(n - 2):
            l1.append(l[i] + l[i + 1])
        l1.append(1)
        l = l1
        yield l
        n = n + 1


print('杨辉三角:')
for item in triangles(15):
    print(item)

# 迭代器 for循环类型
# 1.集合数据类型:list,tuple,dict,st,str(可迭代)
# 2.generator生成器(迭代对象)
# 区分概念 可迭代Iterable 和 迭代对象Iterator不同
# 只有可以使用next方法的对象才是Iterator对象
print('list是可迭代', isinstance([], Iterable))
print('list不是迭代对象', isinstance([], Iterator))

# 函数式编程
# 将大段代码通过一层层函数调用分解成简单任务(属于面向过程的函数设计)

# 高阶函数(函数可作为参数)
# 函数名也是变量名,注意切记不要将变量名写成函数函数名,否则会覆盖
fun = abs
print("abs 名称:", abs, abs(-10))
print("fun 名称:", fun, fun(-10))
fun = 10
print("abs函数名被覆盖:", fun)


# 函数可作为参数传入
def fun(value):
    return -value


def add(x, y, f):
    return f(x) + f(y)


print('函数作为参数传入结果:', add(-10, -5, fun))


# 各种内建函数 返回数据都是Iterator需要list转换
# map 转换函数 将数据转换成另外一组数据
# 函数式 fx = x`2
def fun(x):
    return x * x


l = map(fun, [1, 2, 3, 4, 5, 6, 7, 8, 9])
print('使用map转换的结果:', list(l))


# filter 过滤函数,用来过滤序列
def is_odd(n):
    return n % 2 == 1


print('使用filter转换结果;', list(filter(is_odd, [1, 2, 3, 4, 5, 6, 7, 8, 9])))

# sorted 排序函数
l = [35, 5, -12, 9, -21]
print('没有添加操作函数的排序:', sorted(l))
print('根据绝对值的排序:', sorted(l, key=abs))
print('根据绝对值,反向排序:', sorted(l, key=abs, reverse=True))


# 返回函数
# 函数作为返回值,结果返回函数,需要f()得到真正结果
def lazy_sum(*args):
    def sum():
        ax = 0
        for n in args:
            ax = ax + n
        return ax

    return sum


f = lazy_sum(1, 3, 5, 7, 9)
print('f 结果', f)
print('f() 结果', f())


# 匿名函数 lambda ,只能处理一些接单情况

# 普通函数
def fun1(x):
    return x + x


f1 = fun1

# lambda函数
f2 = lambda x: x + x

print('普通函数结果', f1(10))
print('lambda函数结果',f2(10))


# 装饰器 增加某个函数功能,并不改变原有代码称之为装饰器


