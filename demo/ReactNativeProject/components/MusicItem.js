/**
 * 音乐头部组件
 * https://github.com/facebook/react-native
 * @flow
 */
import React, { Component } from 'react';
var Video = require('react-native-video').default;//引入video组件

import {
  StyleSheet,
  PixelRatio,
  Text,
  Image,
  View,
  TouchableHighlight,
  ListView
} from 'react-native';

class MusicItem extends Component{
   constructor(props) {
     super(props);
        //设置显示数据
     var ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
     this.state = {
       dataSource: ds.cloneWithRows(this._genRows),
       data : [],
     }
   }

   notifyDataChane(dataList){
     this.setState({ dataSource: this.state.dataSource.cloneWithRows(dataList), data:dataList })
   }

    _genRows(){
       return this.props.data;
    }

    _OnItemClick(itemData, sectionID ,rowI){
//        alert(itemData +"  sectionID: "+sectionD+"  rowI: "+rowI);
        this.props.onItemClick(rowI);
    }

    render(){
           return (
               //填充数据
              <ListView
                  dataSource={  this.state.dataSource }
                  renderRow={
                  (rowData, sectionID, rowI)=>
                  //设置点击事件
                  <TouchableHighlight  activeOpacity={0.7}  underlayColor={'#E8E8E8'} onPress={this._OnItemClick.bind(this, rowData.name, sectionID, rowI)} >
                      <View style={ [styles.item_layout,styles.lineCenter] }>
                          <Text>
                              { rowData.name }
                          </Text>
                      </View>
                  </TouchableHighlight>
                  }
                />
        )
    }
}

const styles = StyleSheet.create({
  item_layout: {
   height:52,
   paddingLeft:20,
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

  lineCenter:{
    borderBottomWidth:1/PixelRatio.get(),
    borderColor:'#D3D3D3',
  },

});

module.exports = MusicItem;