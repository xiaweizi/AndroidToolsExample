## 前言

`Android`开发在所难免的就是`UI`的预览和调整，一般情况下都是直接`run`看效果，或者是使用`AS`的`preview`预览，但这同样带来个小问题，就是你的测试内容会跟随着代码被打包到`apk`中，如果没做容错的处理很有可能将测试内容展示给用户。

还有就是像一些列表是不支持预览效果的，比如`ListView`。

其实`Android`老早就有`tools`命名空间，作为一个开发快到一年的我，最近看到一篇文章才发现这个`tools`大用处，在此做个笔记。

直接看一下效果，最为清楚：

![ListView 预览.png](http://upload-images.jianshu.io/upload_images/4043475-d4d0aa1a853870c1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![GridView 预览.png](http://upload-images.jianshu.io/upload_images/4043475-55f190ae724d6e32.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

`XML`中的代码区区几行，但是右侧的`preview`效果却很直接，还有就是这些数据并不会打到`apk`中，直接运行是没有任何效果的，这就是厉害之处！相关代码已提交到`Github`上: [Android Tools](https://github.com/xiaweizi/AndroidToolsExample)

## 初步使用

既然是命名空间，那肯定就要在`XML`开头就要声明。

    xmlns:tools="http://schemas.android.com/tools"
    
类似于

    xmlns:android="http://schemas.android.com/apk/res/android"
    
然后就可以使用`tools`的相关属性了。属性功能很多很全，我就介绍一下常用的，文尾会贴上相关的文章链接。

**View 相关**

先从`view`相关说起，在`XML`中`view`的`android`任意属性值，可以直接替换成`tools`，这样就可以实现实时的预览效果，并且正式部署之后不会展示。有点类似于`dataBinding`中的 `default`属性,以`TextView`为例。
    
    //1. 原生
    android:text="test text"
    //2. databinding
    android:text='@{viewModel.content, default="test text"}'
    //3. tools
    tools:text="test text"
    
`preview`的效果是一样的，不过除了 1 以外，其他运行效果是根据代码中设置的内容决定的，真正做到了测试预览。

之前也说了，`view`的其他属性它都支持。

**context**

通过

    tools:context="com.xiaweizi.androidtoolsexample.CommonActivity"
    
的方式在根部局引用，主要的作用声明该布局文件默认关联的`Activity`，引号传`Activity`类的完整包名。随之就可以开启一些与其相关的特性。比如日常点击事件中，就可以直接在`XML`中声明`onClick`的方法名，然后会有错误的`lint`提示，需要你在`Activity`中创建改方法，跟随着`AS`的智能指引，便可创建对应的方法。点击事件的处理便可以放在里面。

**showIn**

    tools:showIn="@layout/activity_other"
    
在使用`include`或者`merge`时，内层布局是预览不到外层的布局的。那通过`showIn`链接到外层布局，就可以直接连同外层布局一同展示出来。

![showIn](http://upload-images.jianshu.io/upload_images/4043475-3e1ce6356b84a181.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


**layout**

同样在`XML`中引用`fragment`布局也是预览不到效果的：

![无](http://upload-images.jianshu.io/upload_images/4043475-3851aafa5a627d4e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

那通过：

    <fragment
        android:id="@+id/fragment"
        android:name="com.xiaweizi.androidtoolsexample.CommonFragment"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:layout="@layout/layout_fragment_test" />

的方式即可达到预览的效果。

![layout.png](http://upload-images.jianshu.io/upload_images/4043475-8fcbb971d5849ce5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 进阶使用

`tools`除去上面的功能，更强大的在于支持列表的预览展示，也就是文章开头预览的效果，效果最明显的就是`ListView`。

**ListView**

![ListView 预览.png](http://upload-images.jianshu.io/upload_images/4043475-d4d0aa1a853870c1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

    // 1. 列表内容的布局
    tools:listitem="@layout/list_content"
    // 2. 头布局的预览展示
    tools:listheader="@layout/list_header"
    // 3. 脚布局的预览展示
    tools:listfooter="@layout/list_footer"
    
> 一开始使用的时候发现不生效，后来发现需要给`ListView`设置`id`，就会立刻生效了。

**GridView**

![GridView 预览.png](http://upload-images.jianshu.io/upload_images/4043475-55f190ae724d6e32.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

同样也适用于`GridView`。

**RecyclerView**

![RecyclerView](http://upload-images.jianshu.io/upload_images/4043475-7f970e6c6ebebf55.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可惜的是`RecyclerView`只支持`itemCount`这个属性。

**sample** 

有人会好奇列表的数据哪来的，没看你设置，竟然能做到每条数据都不一样。客官别急，接下来就是介绍一下`@tools:sample/*`的强大功能，作为预览视图的占位数据。看一看官网的使用介绍。

![sample](http://upload-images.jianshu.io/upload_images/4043475-e000658ceeea7530.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

各种类型的数据都支持构造，这里我以例子作为展示：

![展示](http://upload-images.jianshu.io/upload_images/4043475-0088a7a62e7a0511.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 每次使用，都会随机的更换不同的值，详情参见列表展示数据。

你以为这样就结束了吗？`too young`！除去原生给的数据支持，咱们也可以自己构造假的数据。

![sample data](http://upload-images.jianshu.io/upload_images/4043475-2a20ff2392642e85.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

直接可以创建`sample data`，然后就可以创建文本或者`json`数据。如果是`json`数据，你必须先运行一下才能使用。那看一下如何使用。

先创建`json`数据：

    {
      "data": [
        {
          "name": "张三",
          "phone": "@tools:sample/us_phones",
          "time": "@tools:sample/date/hhmmss",
          "avatar": "@tools:sample/avatars"
        },
        {
          "name": "李四",
          "phone": "@tools:sample/us_phones",
          "time": "@tools:sample/date/hhmmss",
          "avatar": "@tools:sample/avatars"
        },
        {
          "name": "赵五",
          "phone": "@tools:sample/us_phones",
          "time": "@tools:sample/date/hhmmss",
          "avatar": "@tools:sample/avatars"
        },
        {
          "name": "王二麻",
          "phone": "@tools:sample/us_phones",
          "time": "@tools:sample/date/hhmmss",
          "avatar": "@tools:sample/avatars"
        }
      ]
    }

然后直接在`XML`中，通过`@sample/`的方式拿到数据。

![最终预览](http://upload-images.jianshu.io/upload_images/4043475-4eadb8c507756bbe.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


使用和预览正如上图所示。

## 总结

`get`了该技能，不说有多大用处吧，至少可以提高开发效率，和减少把测试代码部署到线上的失误率吧，相信会帮助到你的！

## 感谢

[tools 官方文档](https://developer.android.google.cn/studio/write/tool-attributes.html)


[命名空间介绍](http://blog.csdn.net/p106786860/article/details/53943540)

[tools 大有用处](https://www.jianshu.com/p/2912bcba4465)
