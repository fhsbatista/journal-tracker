import 'package:flutter/foundation.dart';

abstract class SplashPresenter implements Listenable {
  Stream<String?> get navigateToStream;
  Future<void> init({int delayInSeconds});
}