import React,{useState, useReducer} from 'react';
import { StyleSheet, Text, View, TouchableHighlight ,TouchableOpacity,Image} from 'react-native';
import MyBtton from './src/MyBtton';
import textstyle from './src/textstyle';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { createStackNavigator } from '@react-navigation/stack';
import HomeScreen from './src/screens/HomeScreen';
import ProfileScreen from './src/screens/ProfileScreen';
import Ionicons from 'react-native-vector-icons/Ionicons';
import HomeDetailScreen from './src/screens/HomeDetailScreen';
import ProfileDetailScreen from './src/screens/ProfileDetailScreen';



const Tab = createBottomTabNavigator();
const Stack = createStackNavigator();

function MyHomeStack(){
  return(
   <Stack.Navigator
    initialRouteName='Home'
    screenOptions={{
      headerStyle:{backgroundColor:'tomato'},
      headerBackTitle:'返回',
      headerTintColor:'white'
    }}
    >
      <Stack.Screen name="Home" component={HomeScreen} />
      <Stack.Screen name="HomeDetailScreen" component={HomeDetailScreen} options={{title:'返回'}} />
    </Stack.Navigator> 
  )
}

function MyProfileStack(){
  return(
    <Stack.Navigator
    initialRouteName='Profile'
    screenOptions={{
      headerStyle:{backgroundColor:'tomato'},
      headerBackTitle:'返回',
      headerTintColor:'white'
    }}
    >
      <Stack.Screen name="Profile" component={ProfileScreen} />
      <Stack.Screen name="ProfileDetailScreen" component={ProfileDetailScreen} options={{title:'返回'}} />
    </Stack.Navigator> 
  )
}


export default function App() {

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
    // <View style={styles.container}>
    //   <MyBtton  backgroundColor={'black'} color={'yellow'} onPress={()=>MyBttonprsee()}/>
    //   <Text style={styles.mainText}>Open up App.js to start working on their app!</Text>
    //   <TouchableHighlight style={[{width:100,height:100},textstyle.Te]} onPress={()=>printHighlight()}>
    //   <Image
    //     style = {{width:100,height:100}}
    //     source={{uri:'./src/img/atm.png'}}
    //     />
    //   </TouchableHighlight>

    //   <TouchableOpacity style={{width:100,height:100,backgroundColor:'red',backgroundColor:'yellow'}} onPress={()=>printOpacity()}>
    //     <Image
    //     style = {{width:100,height:100}}
    //     source={{uri:'https://lh3.googleusercontent.com/proxy/VoM0-hoZPmiw_vAbH9Z-mupPumbvVSNghPQP4qwtfId8zsrMZdT8AcFAERU3JreyUUPke-gr8LP_wQiq0qBxKlu3kcTcQEWKed2CON8gbXznVB_LBXw5Zl6uwd1BSPpWTnUSHvqaFlsCT7ivQoc0T_0qi2SL2wfV3PWWETElbx368gbGZw'}}
    //     />

    //   </TouchableOpacity>


    // </View>
    
    <NavigationContainer>

    {/* // <Stack.Navigator
    // initialRouteName='Home'
    // screenOptions={{
    //   headerStyle:{backgroundColor:'tomato'},
    //   headerBackTitle:'返回',
    //   headerTintColor:'white'
    // }}
    // >
    //   <Stack.Screen name="Home" component={HomeScreen} />
    //   <Stack.Screen name="HomeDetailScreen" component={HomeDetailScreen} options={{title:'返回'}} />
    // </Stack.Navigator> */}

      <Tab.Navigator
      initialRouteName='Home'
      screenOptions={({route})=>({
        tabBarIcon: ({color,focused})=>{
          let iconName
          if(route.name == 'Home'){
            // iconName = focused ? 'ios-trophy' : 'ios-information-circle-outline'
           return <Image style={{width:30,height:30}} source={{uri:'https://cdn.ready-market.com/101/36acf849//Templates/pic/Series%2022mm.jpg?v=873b4f15'}}  />
          }else if(route.name =='Settings'){
          iconName = 'ios-options'
        }
        return <Ionicons name={iconName} size={25} color={color}/>
      }
      })}
      tabBarOptions={{
        activeTintColor:'tomato',
        inactiveTintColor:'gray'
      }}
      >
        <Tab.Screen name="Home" component={MyHomeStack} />
        <Tab.Screen name="Settings" component={MyProfileStack} />
      </Tab.Navigator>
    </NavigationContainer>

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
