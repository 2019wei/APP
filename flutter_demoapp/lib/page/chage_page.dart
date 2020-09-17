import 'package:flutter/material.dart';

class ChagePage extends StatelessWidget {
  final String title;

  ChagePage({Key key, this.title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Center(
          child: Text('$title'),
        ),
      ),
      body: Center(
        child: Text('$title'),
      ),
    );
  }
}
