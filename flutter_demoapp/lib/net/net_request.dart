import 'package:dio/dio.dart';

class ComResponse<T>{
  int code;
  String msg;
  T data;

  ComResponse({this.code,this.msg,this.data});
}

class NetRequest {
  var dio = Dio();

  Future<ComResponse<T>> requestData<T>(String path,
      {Map<String, dynamic> querParameters,
      dynamic data,
      String method = "get"}) async {
    try{
      Response response = method == "get"
          ? await dio.get(path, queryParameters: querParameters)
          : await dio.post(path, data: data);

      return ComResponse(
          code: response.data['code'],
          msg: response.data['msg'],
          data: response.data['data']
      );
    } on DioError catch(e){
      //DioError 只會返回服務器的錯誤 500
      print(e.message);
      String message = e.message;
      if(e.type == DioErrorType.CONNECT_TIMEOUT)
        message = 'Connection TimeOut';
      else if(e.type == DioErrorType.RECEIVE_TIMEOUT)
        message = 'Receive Timeout';
      else if(e.type == DioErrorType.RESPONSE){
        message = '404 server not found ${e.response.statusCode}';
      }
      return Future.error(message);
    }

  }
}

//xxx.xxx.com/api/profile/test?name=米斯克&age=32  get
//xxx.xxx.com/api/profile/test  body              post
