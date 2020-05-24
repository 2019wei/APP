import React from 'react';
import { StyleSheet, Text, View, TouchableHighlight ,TouchableOpacity,Image} from 'react-native';
import  propTypes from 'prop-types'

const MyBtton=(props)=>{
   
    return(
    <TouchableOpacity onPress={props.onPress}>
        <Text style={{backgroundColor:props.backgroundColor,color:props.color}}>{props.myTitle}</Text>
    </TouchableOpacity>

)
}
export default MyBtton

MyBtton.propTypes={
    myTitle: propTypes.string.isRequired,
    myOnPress:propTypes.func
}

MyBtton.defaultProps={
    myTitle:'預設'
}