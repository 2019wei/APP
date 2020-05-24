import React,{useState, useReducer} from 'react';
import { StyleSheet, Text, View, TouchableHighlight ,TouchableOpacity,Image, Button} from 'react-native';
import MyBtton from '../MyBtton';
import textstyle from '../textstyle';

export default function HomeDetailScreen(props) {

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
  const name = props.route.params.name || 'nothing get'

  const passProps = props.route.params.passProps || 'nothing'

  return (
    <View style={styles.container}>
        <Text>HomeDetailScreen</Text>
        <Text>{passProps.animal_place}</Text>
      {/* <MyBtton  backgroundColor={'black'} color={'yellow'} onPress={()=>MyBttonprsee()}/>
      <Text style={styles.mainText}>HomeDetailScreen!</Text>
      <Button title="返回" onPress={()=>{return props.navigation.pop()}}/>
      <Text>{name}</Text>
      <Button
      title='change first page food'
      onPress={()=>props.route.params.functionA('apple')}
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
  textAlign:"center",
  fontSize:20,
  color:'red'
},
commandText:{
  color:'green'
}
});
