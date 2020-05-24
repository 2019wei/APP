import React,{useState, useReducer, useEffect} from 'react';
import { StyleSheet, Text, View, TouchableHighlight ,TouchableOpacity,Image, Button} from 'react-native';
import MyBtton from '../MyBtton';
import textstyle from '../textstyle';
import * as StorageHelper from './StorageHelper'

export default function ProfileScreen(props) {

  const [myBookCount,setMyBookCount] = useState(0)
  const [myBookListName,setMyBookListName] = useState([])

  useEffect(()=>{
    const unsubscribe= props.navigation.addListener('focus',()=>{
      loadStorage()
      // console.log('監聽中')
    })
     return unsubscribe
    
  },[myBookCount])

  const loadStorage = async()=>{
    let bookGet = await StorageHelper.getMySetting('myList')
    // 法一
    // let a = JSON.parse(bookGet)
    // let newArray=[]
    // a.forEach((thing)=>{
    //   newArray.push(thing.animal_colour + '的' + thing.animal_kind)
    // })
    // setMyBookCount(a.length)
    // setMyBookListName(newArray)

    // 法二
    let a = JSON.parse(bookGet)
    setMyBookCount(a.length)
    setMyBookListName(a)
    

  }

  // const food = 'noodles'
  const [food,setFood] = useState('candy')
  const [count,setCount] = useState(0)

  const printHighlight= () =>{
    console.log('按到printHighlight')
  }

  const printOpacity = () =>{
    console.log('按到printOpacity')
  }
  const MyBttonprsee = () =>{
    console.log('點到自創元件')
  }

  return (
    <View style={styles.container}>
      <Text>ProfileScreen</Text>
      <Text>我收藏了{myBookCount}個寵物認養</Text>

      {/* 法一渲染 */}
      {/* {
        myBookListName.map((pet,index)=>{
        return(<Text key={index}>認養寵物為:{pet}</Text>)
        })
      } */}
      {/* 法二渲染 */}
      {
        myBookListName.map((pet,index)=>{
        return (<Text key={index}>認養寵物為:{pet.animal_colour+'的'+pet.animal_kind}</Text>)
        })
      }


      {/* <MyBtton  backgroundColor={'black'} color={'yellow'} onPress={()=>MyBttonprsee()}/>
      <Text style={styles.mainText}>ProfileScreen!</Text>
      <Button title={'go to profile detail screen'} 
      onPress={()=>props.navigation.push('ProfileDetailScreen')}/>
      
      <TouchableHighlight style={[{width:100,height:100},textstyle.Te]} onPress={()=>printHighlight()}>
      <Image
        style = {{width:100,height:100}}
        source={{uri:'./src/img/atm.png'}}
        />
      </TouchableHighlight>

      <TouchableOpacity style={{width:100,height:100,backgroundColor:'red',backgroundColor:'yellow'}} onPress={()=>printOpacity()}>
        <Image
        style = {{width:100,height:100}}
        source={{uri:'https://lh3.googleusercontent.com/proxy/VoM0-hoZPmiw_vAbH9Z-mupPumbvVSNghPQP4qwtfId8zsrMZdT8AcFAERU3JreyUUPke-gr8LP_wQiq0qBxKlu3kcTcQEWKed2CON8gbXznVB_LBXw5Zl6uwd1BSPpWTnUSHvqaFlsCT7ivQoc0T_0qi2SL2wfV3PWWETElbx368gbGZw'}}
        />

      </TouchableOpacity> */}


    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
mainText:{
  textAlign:"center",
  fontSize:20,
  color:'red'
},
commandText:{
  color:'green'
}
});
