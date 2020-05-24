import React,{useState, useReducer,useEffect} from 'react';
import { StyleSheet, Text, View, TouchableHighlight ,TouchableOpacity,Image,Button,FlatList} from 'react-native';
import MyBtton from '../MyBtton';
import textstyle from '../textstyle';
import * as StorageHelper from './StorageHelper'

var MOCKED_DATA2 = [{
  id:'1',
    note:'最新最新製單CA123456789',
    date:'2020-05-19 14:00:00'
}]
var MOCKED_DATA =[
  {
    id:'1',
    note:'製單CA123456789',
    date:'2020-05-19 14:00:00'
  },
  {
    id:'2',
    note:'製單CB666666666',
    date:'2020-05-19 15:00:00'
  },
  {
    id:'3',
    note:'製單CD111111111',
    date:'2020-05-19 16:00:00'
  },
  {
    id:'4',
    note:'製單CA222222223',
    date:'2020-05-19 14:00:00'
  },
  {
    id:'5',
    note:'製單CB333333333',
    date:'2020-05-19 15:00:00'
  },
  {
    id:'31',
    note:'製單CD111111111',
    date:'2020-05-19 16:00:00'
  },
  {
    id:'12',
    note:'製單CA123456789',
    date:'2020-05-19 14:00:00'
  },
  {
    id:'22',
    note:'製單CB666666666',
    date:'2020-05-19 15:00:00'
  },
  {
    id:'34',
    note:'製單CD111111111',
    date:'2020-05-19 16:00:00'
  },
]
export default function HomeScreen(props) {

  // const food = 'noodles'
  // https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=QcbUEzN6E6DL
 

  const fetchData=()=>{
    const REQUEST_URL = 'https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=QcbUEzN6E6DL'
    fetch(REQUEST_URL)
    .then(response=>response.json())
    .then(responseData=>{
      setDataSource(responseData)
    })
    .catch((err)=>{
      console.log('error是 :',err)
    })
  }
  
  const [isloading,setIsloading] = useState(false)
  const [count,setCount] = useState(0)
  const [dataSource,setDataSource] = useState([])

  const printHighlight= () =>{
    console.log('按到printHighlight')
  }

  const printOpacity = () =>{
    console.log('按到printOpacity')
  }
  const MyBttonprsee = () =>{
    console.log('點到自創元件')
  }
  const [food,setFood] = useState('candy')

  function changeFood(foodGet){
    setFood(foodGet)
  }

  useEffect(()=>{
    // var book = MOCKED_DATA
    // setDataSource(book)
    fetchData()
  },[])

  useEffect(()=>{
    let getAll=[]

    dataSource.map(a=>{
      if(a.addToMyLists === true){
        getAll.push(a)
      }
    })
    saveToStroage(getAll)
  })

  const saveToStroage= async (getMyBooks)=>{
    try{
      await StorageHelper.setMySetting('myList',JSON.stringify(getMyBooks))
    }catch(err){
      console.log(err)
    }
   
  }

 const isloaddata = ()=>{
  // var book1 = MOCKED_DATA2
  setIsloading(false)
  fetchData()
  // setDataSource(book1)
 }


  const ShowNoticeDetail = (cases)=>{
    props.navigation.push('HomeDetailScreen',{passProps:cases})
  }
  const pressRow=(cases)=>{
    const newDatas= dataSource.map(a =>{
      let copyA ={...a}
      if(copyA.animal_id=== cases.animal_id){
        copyA.addToMyLists = !copyA.addToMyLists
      }
      return copyA
    })
    setDataSource(newDatas)
  }

  const renderBook=(cases)=>{
    return(
      <TouchableOpacity onPress={()=>ShowNoticeDetail(cases)}>
        <View>
          <View style={styles.mainText}>
            <TouchableOpacity onPress={()=>pressRow(cases)}>
              {cases.addToMyLists === true ?<Image style={styles.imageCheck} source={require('../../src/img/check.png')}/>
              :<Image style={styles.imageCheck}  source={require('../../src/img/uncheck.png')}/>}
            </TouchableOpacity>
            {/* <Image/> */}
            <View style={{flex:1}}>
              <Text ellipsizeMode='tail' numberOfLines={3} style={{color:'black',fontSize:15,marginTop:8}}>
{cases.animal_place}
              </Text>
              <Text ellipsizeMode='tail' numberOfLines={3} style={{marginTop:8,fontSize:13,marginBottom:8}}>
 {cases.animal_kind}               
              </Text>
            </View>
            <Image source={require('../../assets/img/arrow.png')} style={styles.image}/>
            {/* <Image source={require('../../assets/img/plus.png')} style={styles.image}/> */}
          </View>
  <View style={styles.seperator}/>        
        </View>
      </TouchableOpacity>
    )
  }

  return (
    <View>

      <FlatList
      data={dataSource}
      renderItem={cases => renderBook(cases.item)}
      keyExtractor={cases => cases.animal_id.toString()}
      style={{backgroundColor:'white'}}
      refreshing={isloading}
      onRefresh={()=>isloaddata()}
      />


      {/* <MyBtton  backgroundColor={'black'} color={'yellow'} onPress={()=>MyBttonprsee()}/>
      <Text style={styles.mainText}>HomeScreen!</Text>
      <Text>{food}</Text>
      <Button 
      title={'go to next page'}
      onPress={()=>{ return props.navigation.push('HomeDetailScreen',{name:'123',functionA:(arg)=>changeFood(arg)}) }}
      />
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
  height:80,
  flexDirection:'row',
  justifyContent:'center',
  alignItems:'center',
  backgroundColor:'white',
  padding:8
},
seperator:{
  height:1,
  backgroundColor:'#dddddd'
},
image:{
  width:25,
  height:40
},
imageCheck:{
  width:25,
  height:25,
  marginRight:10,
}
});
