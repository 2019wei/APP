import 'dart:async';
import 'dart:io';

import 'package:path_provider/path_provider.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

class DatabaseHelper{
  static final _databaseName = "STUD.db";
  static final _databaseVersion = 1;
  static final table = 'STUD';

  DatabaseHelper._privateConstructor();
  static final DatabaseHelper instance = DatabaseHelper._privateConstructor();
  static Database _database;

  Future<Database>get database async{
    if(_database!=null) return _database;

    _database = await _initDatabase();
    return _database;
  }

  _initDatabase() async{
    Directory documentDirectory = await getApplicationDocumentsDirectory();
    String path = join(documentDirectory.path,_databaseName);
    return await openDatabase(path,version: _databaseVersion,onCreate: _onCreate);

  }


  FutureOr<void> _onCreate(Database db, int version) async{
    await db.execute(''' 
    CREATE TABLE $table (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    score INTEGER NOT NULL
    )
    ''');
  }

  Future<int> insert(Map<String , dynamic> row) async {
    Database db = await instance.database;
    return await db.insert(table, row);
  }

  Future<List<Map<String,dynamic>>> queryAllRows() async {
    Database db = await instance.database;
    return await db.query(table);
  }
  Future<int> queryRowCount() async {
    Database db = await instance.database;
    return  Sqflite.firstIntValue(await db.rawQuery('select count(*) from $table'));
  }

  Future<List<Map<String,dynamic>>> queryRowCount1() async {
    Database db = await instance.database;
    return  await db.rawQuery('select * from  $table  where score = "59" ');
  }

  Future<int> update(Map<String , dynamic> row) async {
    Database db = await instance.database;
    int id = row['id'];
    return await db.update(table, row,where: 'id=?',whereArgs: [id]);
  }
  Future<int> delete(Map<String , dynamic> row) async {
    Database db = await instance.database;
    int id = row['id'];
    return await db.delete(table, where: 'id=?',whereArgs: [id]);
  }

}