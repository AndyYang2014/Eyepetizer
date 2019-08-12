# Eyepetizer-Kotlin

#####      开眼视频是一款精品短视频日报应用，该项目是用kotlin，借助已知的一些开眼接口写的一个仿《开眼App》，主要是为了学习kotlin和一些UI效果

### [点击下载Apk](https://github.com/AndyYang2014/Eyepetizer/raw/master/app/release/app-release.apk)

## Kotlin

  让你的代码量大大减少，lambda、扩展函数、空安全、函数式编程让你爽到飞上天！与此同时告别findViewById直接使用控件id为所欲为。


## 项目模式

* MVP
* [Kotlin](https://github.com/JetBrains/kotlin)
* [Kodein](https://kodein.org/Kodein-DI)
* [Rxjava](https://github.com/ReactiveX/RxJava)
* [Retrofit](https://github.com/square/retrofit)
* [Okhttp3](https://github.com/square/okhttp)
* [Gson](https://github.com/google/gson)
* [Glide](https://github.com/bumptech/glide)
* [GSYVideoPlayer](https://github.com/CarGuo/GSYVideoPlayer)
* [XRecyclerView](https://github.com/XRecyclerView/XRecyclerView)
* [BottomNavigation](https://github.com/Ashok-Varma/BottomNavigation)


## MVP 
  通过LifeCycle接口让View与Presenter通信，并使用OnLifeCycleListener让Presenter持有View层的生命周期。

  * Model -- 主要处理业务，用于数据的获取(如网络、本地缓存)。
  * View -- 用于把数据展示，并且提供交互。
  * Presenter -- View和Model交互的桥梁，二者通过Presenter建立联系。

  主要流程如下： 用户与View交互，View得知用户需要加载数据，告知Presenter，Presenter则告知Model，Model拿到数据反交于Prsenter，Presenter将数据交给View进行展示。


## 参考配置  

    as:3.0   
	grade:3.0.1       
	compileSdkVersion 27
	buildToolsVersion "27.0.1"
	minSdkVersion 17
    targetSdkVersion 27


## 关于我

#### * [GitHub](https://github.com/AndyYang2014)
#### * [个人博客](http://www.andyyang2014.com/)
#### * 邮箱:Andyyang2014@126.com



## 声明
该App仅供学习使用不得用于商业用途，数据接口以及资源全部来自豌豆荚的开眼App与gank.io，如果侵权请联系删除



