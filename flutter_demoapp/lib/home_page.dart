import 'package:flutter/material.dart';

class Home1 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('BBB'),
      ),
      body: Center(
        child: RaisedButton(
          child: Text('返回首頁'),
          onPressed: (){Navigator.pop(context,'BBBBBB');},
        ),
      ),
    );
  }


}
