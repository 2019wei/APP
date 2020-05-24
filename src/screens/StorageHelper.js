import{AsyncStorage} from 'react-native'


export  const setMySetting =(key,value)=> AsyncStorage.setItem(key,value)
export  const getMySetting =(key)=> AsyncStorage.getItem(key)