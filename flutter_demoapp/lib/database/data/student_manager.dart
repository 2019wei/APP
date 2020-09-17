import 'package:flutter_demoapp/database/data/database_helper.dart';
import 'package:flutter_demoapp/database/data/student.dart';

class StudentManager{
  final dbHelper = DatabaseHelper.instance;

  StudentManager._();
  static final StudentManager instance = StudentManager._();

  void insert() {
    var student = Student(name: 'HKT',score: 59);

    dbHelper.insert(student.toMap());
    print('執行insert');
  }

  void query() async {
    final rows = await dbHelper.queryRowCount1();
    print('結果');
    rows.forEach((element) { print(element);});
    print('結束');
  }

  update() async {
    var student = {'id': 1,'name': 'HKT','score': 100};
    await dbHelper.update(student);
    print('update 結束');
  }



}