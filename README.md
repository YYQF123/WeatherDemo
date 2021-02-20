# 红岩寒假考核

## App概览

### 1.概括

类型：天气App

实现接口：和风天气api

### 2.功能

1.实况天气   2.天气预报   3.生活指数   4.多城市查询

### 3.界面

预期有五个界面：

​        主界面，城市管理界面，城市搜索添加界面，城市删除界面，设置界面。

目前只完成前三个界面QAQ。

## 实现思路和详细介绍

### 0

1.数据库（SQLite）：基类的三个属性：_id，JSONContent（也就是response，json数据），CityName（城市名称）

2.分包：基类，数据库，城市操作（城市管理，删除，添加界面）和城市信息显示，这个没有独立分包，也是在网上参考的标准（MainActivity，Fragment和Adapter）

### 1.1 主界面

1.由ViewPager和Fragment进行天气信息显示

基于LinerLayout的菜单栏：

   1.添加按钮：跳转到城市管理界面

   2.设置按钮：跳转到设置界面（未编写）

   3.小点指示器（轮播图下方的点指示器）

### 1.2 MainActivity和Fragment逻辑

MainAcivity：
1.获取数据库中城市信息实例化 cityNameList（List<String>），获取城市添加界面可能传入的cityName(String)
2.通过cityNameList，bundle初始化fragmentList（viewpager的数据源）
3.初始化小点指示器
4.通过给viewpager设置选择事件，与小点指示器联动
5.设置添加按钮的点击事件：跳转到城市管理页面

Fragment:

1.先写了一个基类LoadDataFragment继承Fragment，在里面实现网络请求方法，和onSuccess（response）回调方法，然后让Fragment的实体类WeatherContionFragment继承该基类（网上找的这个操作，感觉很高端就模仿着用了）

2.通过bundle获取城市名称用于拼接url，调用父类网络请求方法，在覆写的onSuccess（）方法中实现数据解析和展示功能。

3.设置生活指数图片的点击事件（未完成QAQ）

### 2.1城市管理界面

主要由Listview构成，每个item都是每个城市天气信息的概括

item：

![1613810847391](../../AppData/Roaming/Typora/typora-user-images/1613810847391.png)

菜单栏：1.返回按钮：返回主界面

2.编辑按钮：跳转城市删除界面（未完成）

3.添加按钮：跳转城市搜索添加界面

### 2.2 CityManageActivity逻辑

1.重写onResume（）方法（查阅得知该方法是获取焦点，准备交互前调用的方法），在该方法中更新数据源，获取数据库中当前存储的全部城市信息，通知适配器更新。

2.点击事件

### 3.1城市添加界面

EditView+ImageView

搜索框加提交按钮

### 3.2CityAddActivity逻辑

设置ImageView的点击事件：获取输入的文本，先判断输入是否为空，为空则toast提示为空；
不为空则调用封装的获取ID方法

（解释：我先说明这个点击事件的最终目的：将输入的文本也就是城市名通过intent传给MainActivity来添加城市，对应刚才主活动的第一条解释。现状是和风api要先通过名称获取LocationID，再用ID获取数据，所以在这里先用输入的文本拼接url进行获取ID操作（调用HttpGetCityLocation方法类），在onSuccess方法里面直接跳转传值），

获取失败则toast提示获取失败；
获取成功则拼接url，调用获取天气数据方法，
如果成功，则通过intent向主活动跳转并传递文本内容

## 待提升的地方

1.删除界面，设置界面，小点指示器，指数点击事件还未完成

2.因为模仿的那个继承基类的操作，结果我的网络请求和数据解析代码又重复又很乱，感觉很不好

3.天气api审核未通过，所以为了让app能多少达到预期效果，我就自己造了一些类和数据，目前看起来还可以，但是联网后的效果还不知道

