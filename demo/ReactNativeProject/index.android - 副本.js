/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
//引入包
import React, { Component } from 'react';

import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

//引入自定义组件
var MusicHeader = require('./components/MusicHeader');
var MusicItem = require('./components/MusicItem');
var VideoDemo = require('./components/VideoDemo');

//ui
export default class ReactNativeProject extends Component {

//    生命周期介绍
//
//    //执行过一次后，被创建的类会有缓存，映射的值会存在this.pro前提是这个prop不是父组件指定的
//    //这个方法在对象被创建之前执行，因此不能在方法内调用this.props ，另外，注意任何getDefaultProps()返回的对象在实例中共享，不是复制
//    getDefaultProps(){
//    //todo log
//    }
//
//    //控件加载之前执行，返回值会被用于state的初始化值
//    getInitialState(){
//    //todo log
//    }
//
//    //执行一次，在初始化render之前执行，如果在这个方法内调用setState，render()知道state发生变化，并且只执行一次
//    componentWillMount(){
//    //todo log
//    }

    /**
    * render的时候会调用render()会被调用
    * 调用render()方法时，首先检查this.props和this.state返回一个子元素，子元素可以是DOM组件或者其他自定义复合控件的虚拟实现
    * 如果不想渲染可以返回null或者false，这种场景下，react渲染一个<noscript>标签，当返回null或者false时，ReactDOM.findDOMNode(this)返回null
    * render()方法是很纯净的，这就意味着不要在这个方法里初始化组件的state，每次执行时返回相同的值，不会读写DOM或者与服务器交互，如果必须如服务器交互，
    * 在componentDidMount()方法中实现或者其他生命周期的方法中实现，保持render()方法纯净使得服务器更准确，组件更简单
    **/
//     <MusicHeader></MusicHeader>
//            <MusicItem></MusicItem>
    render(){
        return (
        <View>

            <VideoDemo></VideoDemo>
        </View>
        )
    }

//  renderScene(route,navigator){
//    var routeId = route.id;
//    if(routeId === 'Login'){
//      return (
//        <HelloWorld navigator={navigator}/>
//      )
//    }
//  }

//    //在初始化render之后只执行一次，在这个方法内，可以访问任何组件，componentDidMount()方法中的子组件在父组件之前执行
//    //从这个函数开始，就可以和 js 其他框架交互了，例如设置计时 setTimeout 或者 setInterval，或者发起网络请求
//    componentDidMount(){
//    //todo log
//    }
//
//    //这个方法在初始化render时不会执行，当props或者state发生变化时执行，并且是在render之前，当新的props或者state不需要更新组件时，返回false
//    shouldComponentUpdate(nextProps, nextState){
//        //当shouldComponentUpdate方法返回false时，讲不会执行render()方法，componentWillUpdate和componentDidUpdate方法也不会被调用
//        //默认情况下，shouldComponentUpdate方法返回true防止state快速变化时的问题，但是如果·state不变，props只读，可以直接覆盖shouldComponentUpdate
//        //用于比较props和state的变化，决定UI是否更新，当组件比较多时，使用这个方法能有效提高应用性能
//        return true;
//    }
//
//    //当props和state发生变化时执行，并且在render方法之前执行，当然初始化render时不执行该方法，需要特别注意的是，在这个函数里面，你就不能使用this.setState来修改状态。
//    //这个函数调用之后，就会把nextProps和nextState分别设置到this.props和this.state中。紧接着这个函数，就会调用render()来更新界面了
//    componentWillUpdate(object nextProps, object nextState){
//    //todo log
//    }
//
//    //组件更新结束之后执行，在初始化render时不执行
//    componentDidUpdate( object prevProps, object prevState){
//    //todo log
//    }
//
//    //当props发生变化时执行，初始化render时不执行，在这个回调函数里面，你可以根据属性的变化，通过调用this.setState()来更新你的组件状态，
//    //旧的属性还是可以通过this.props来获取,这里调用更新状态是安全的，并不会触发额外的render调用
//    componentWillReceiveProps(){
//    //todo log
//    }
//
//    //当组件要被从界面上移除的时候，就会调用componentWillUnmount(),在这个函数中，可以做一些组件相关的清理工作，例如取消计时器、网络请求等
//    componentWillUnmount(){
//    //todo log
//    }
}

//样式设置，即组件的显示样式,可以使用多套样式，但是有重复样式时，后面样式将会覆盖前面样式
const styles = StyleSheet.create({

  container: { flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },

});


//程序入口组件使用AppRegistry.registerComponent来注册。当注册完应用程序组件后，Native系统（OC）就会加载jsbundle文件并触发AppRegistry.runApplication运行应用。AppRegistry有以下方法：
//registerConfig(config:Array): 静态方法，注册配置。
//registerComponent(appKey:string,getComponentFunc: ComponentProvider): 注册入口组件。
//registerRunnable(appKey:string , func :Function): 注册函数监听。
//getAppKeys(): 获取registerRunnable注册的监听键。
//runApplication(appKey:string,appParameter:any): 运行App
AppRegistry.registerComponent('ReactNativeProject', () => ReactNativeProject);
