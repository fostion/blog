## Android_AIDL开发

介绍：`AIDL`Android跨进程通信接口,一般配合Service使用

#### 具体开发流程:
##### 1.开一个`aidl`文件夹，创建`相同包名`的路径,在里面创建`.aidl`文件
##### 2.aidl内容

```java
// 方法不允许重名
//IMySampe.adil

package cc.tv.sample;
import cc.tv.sample.Person; //必须导入包，否则无法识别类

interface IMySampe {

    void openNumber(int number);

    void openName(String name);

    void next();

    void prev();

    //in 代表客户端输入实现Parcelable，否则一直报错
    void tanslageData(in Person data);

    List<Person> getList();
}

//Person.java
public class Person implements Parcelable {

    String id;
    String name;
    String code;


    public Person(String id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.code);
    }

    public Person() {
    }

    protected Person(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.code = in.readString();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}


//person.aidl
package cc.tv.sample;
parcelable Person; //必须写，每个java，都要一个文件

```

##### 3.gradle配置

```gradle

sourceSets {
    main {
        manifest.srcFile 'src/main/AndroidManifest.xml'
        java.srcDirs = ['src/main/java', 'src/main/aidl'] //必须写
        resources.srcDirs = ['src/main/java', 'src/main/aidl']
        aidl.srcDirs = ['src/main/aidl'] //必须写
        res.srcDirs = ['src/main/res']
        assets.srcDirs = ['src/main/assets']
    }
}
```


##### 4.Service文件填写

```java
//RemoteService.java
public class RemoteService extends Service {
private final IMySampe.Stub binder = new IMySampe.Stub() {
    @Override
    public void openNumber(int number) throws RemoteException {
    }

    @Override
    public void openName(String name) throws RemoteException {
    }

    @Override
    public void next() throws RemoteException {
    }

    @Override
    public void prev() throws RemoteException {
    }

    @Override
    public void tanslageData(Person data) throws RemoteException {
    }

    @Override
    public List<Person> getList() throws RemoteException {
        List<Person> persons = new ArrayList<>();
        return persons;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
};

//xml
<service
  android:exported="true" //允许外部应用调用Service否则无法调用
  android:name=".aidlsample.RemoteService"  />

```

##### 5.bindeService

```java
IMySampe sampleAIDL;
ServiceConnection conn = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        sampleAIDL = IMySampe.Stub.asInterface(iBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {

    }
};

//bindService
Intent intent = new Intent(this,RemoteService.class);
or
//隐式启动应用要包名，否则在5.0以上或出错，包名是写开启服务的应用,在log上可以看到
Intent intent = new Intent("yourAction");
intent.setPackage("servicePageName");


//bind注意这里有个坑BIND_AUTO_CREATE绑定时候自动创建
bindService(intent,conn, Context.BIND_AUTO_CREATE);

//unbind
unbindService(conn)
```
