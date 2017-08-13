/**
 * 音乐头部组件
 * https://github.com/facebook/react-native
 * @flow
 */
import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  Image,
  StatusBar,
  View,
  TouchableHighlight,
} from 'react-native';

class MusicHeader extends Component{

    _OnClick(){
        this.props.search_local_music();
    }

    render(){
        return (
        <View style={ styles.head_layout }>
           <StatusBar  backgroundColor="#FF3030" barStyle="light-content" />
          <Text style={ styles.head_text } >本地音乐</Text>
                <View style={ styles.image_layout }>
                   <TouchableHighlight  activeOpacity={0.55}  underlayColor={'#FF3030'} onPress={this._OnClick.bind(this)} >
                      <Image source={ require('../icon/ic_search.png')}
                           style={ styles.head_icon }/>
                   </TouchableHighlight>
                </View>
        </View>
        )
    }
}

const styles = StyleSheet.create({
  head_layout: {
   height:48,
   backgroundColor:'#FF3030',
   justifyContent :'center',
  },

  head_text:{
    position:'absolute',
    color:'#fff',
    marginLeft:20,
    fontSize:20,
    fontWeight:'bold',
    alignSelf:'flex-start',
  },

   image_layout: {
     position:'absolute',
     paddingRight:20,
     backgroundColor:'#FF3030',
     justifyContent :'center',
     alignSelf:'flex-end',
    },

  head_icon:{
    width:28,
    height:28,
    alignSelf:'flex-start',
  }
});

module.exports = MusicHeader;