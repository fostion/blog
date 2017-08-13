'use strict';

import React, {
  Component
} from 'react';

import {
  AppRegistry,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  Image,
  PixelRatio,
  Date,
} from 'react-native';

import Video from 'react-native-video';


var musicData = {
     path : "",
     name : "点击选择播放",
  };



class Player extends Component {

play_icons = [
   require('../icon/ic_play.png'),
   require('../icon/ic_stop.png'),
];

  state = {
    rate: 1,
    volume: 1,
    muted: false,
    resizeMode: 'contain',
    duration: 0.0,
    currentTime: 0.0,
    title:'',
    url:'',
    paused: true,
    iconIndex:0,
  };

  video: Video;

  onLoad = (data) => {
    this.setState({ duration: data.duration });
  };

  onProgress = (data) => {
    this.setState({ iconIndex: 1 ,currentTime: data.currentTime });
  };

  onEnd = () => {
    this.video.seek(0)
    this.props.next();
  };

  play_next= () => {
    this.video.seek(0)
    this.props.next();
  }

  onAudioBecomingNoisy = () => {
    this.setState({ paused: true })
  };

  onAudioFocusChanged = (event: { hasAudioFocus: boolean }) => {
    this.setState({ paused: !event.hasAudioFocus })
  };

  getCurrentTimePercentage() {
    if (this.state.currentTime > 0) {
      return parseFloat(this.state.currentTime) / parseFloat(this.state.duration);
    }
    return 0;
  };

  notifyUpdate = (data) => {
     musicData = data;
     console.log("界面刷新 ： name: "+musicData.name +"  path: "+musicData.path);
     this.setState({ paused: false ,title: musicData.name, url: musicData.path})
  }

  renderRateControl(rate) {
    const isSelected = (this.state.rate === rate);

    return (
      <TouchableOpacity onPress={() => { this.setState({ rate }) }}>
        <Text style={[styles.controlOption, { fontWeight: isSelected ? 'bold' : 'normal' }]}>
          {rate}x
        </Text>
      </TouchableOpacity>
    );
  }

  renderResizeModeControl(resizeMode) {
    const isSelected = (this.state.resizeMode === resizeMode);

    return (
      <TouchableOpacity onPress={() => { this.setState({ resizeMode }) }}>
        <Text style={[styles.controlOption, { fontWeight: isSelected ? 'bold' : 'normal' }]}>
          {resizeMode}
        </Text>
      </TouchableOpacity>
    )
  }

  renderVolumeControl(volume) {
    const isSelected = (this.state.volume === volume);

    return (
      <TouchableOpacity onPress={() => { this.setState({ volume }) }}>
        <Text style={[styles.controlOption, { fontWeight: isSelected ? 'bold' : 'normal' }]}>
          {volume * 100}%
        </Text>
      </TouchableOpacity>
    )
  }

  render() {
    const flexCompleted = this.state.currentTime;
    const flexRemaining = this.state.duration - this.state.currentTime;
    return (
      <View style={ [styles.player_layout,styles.lineCenter] }>
        <View style={ styles.detail_layout }>
           <TouchableOpacity
                  style={styles.fullScreen}
                  onPress={() => this.setState({ paused: !this.state.paused })}
                >
              <Video
                ref={(ref: Video) => { this.video = ref }}
                /* For ExoPlayer */
                /* source={{ uri: 'http://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0', type: 'mpd' }} */
                source={{ uri: 'file://'+this.state.url, type: 'mp3' }}
//                source={require('../video/music2.mp3')}
                style={styles.fullScreen}
                rate={this.state.rate}
                paused={this.state.paused}
                volume={this.state.volume}
                muted={this.state.muted}
                resizeMode={this.state.resizeMode}
                onLoad={this.onLoad}
                onProgress={this.onProgress}
                onEnd={this.onEnd}
                onAudioBecomingNoisy={this.onAudioBecomingNoisy}
                onAudioFocusChanged={this.onAudioFocusChanged}
                repeat={false}
              />
           </TouchableOpacity>

          <Text style= {styles.name_text} >
            {this.state.title}
          </Text>
        </View>

          <View style={ styles.image_layout }>
             <TouchableOpacity
                 onPress={() => {
                   this.setState({ iconIndex : 0,paused: !this.state.paused })
                 }}
                >
              <Image source={ this.play_icons[this.state.iconIndex]}
                     style={ styles.image_icon }/>
             </TouchableOpacity>

          <TouchableOpacity
                 onPress={this.play_next}
               >
         <Image source={ require('../icon/ic_next.png')}
                     style={ [styles.image_icon, styles.image_icon_right] }/>
          </TouchableOpacity>

          </View>

        <View style={styles.progress}>
             <View style={[styles.innerProgressCompleted, { flex: flexCompleted }]} />
             <View style={[styles.innerProgressRemaining, { flex: flexRemaining }]} />
        </View>
      </View>
    );
  }
}


const styles = StyleSheet.create({
  player_layout: {
   flexDirection:'column',
   height:60,
   backgroundColor:'#fff',
   justifyContent:'center',
  },

  lineCenter:{
      borderTopWidth:1/PixelRatio.get(),
      borderColor:'#D3D3D3',
    },

  detail_layout:{
     height:58,
     justifyContent:'center',
    },

  name_text: {
    marginLeft:20,
  },

  progress: {
    flex: 1 ,
    flexDirection: 'row',
    borderRadius: 3,
  },

  innerProgressCompleted: {
    height: 2,
    backgroundColor: '#FF3030',
  },
  innerProgressRemaining: {
    height: 2,
    backgroundColor: '#cccccc',
  },
 image_layout: {
   flexDirection:'row',
   position:'absolute',
   paddingRight:10,
   justifyContent :'center',
   alignSelf:'flex-end',
  },

image_icon:{
  width:34,
  height:34,
  alignSelf:'flex-start',
},

image_icon_right : {
  marginLeft:15,
}

});

module.exports = Player;