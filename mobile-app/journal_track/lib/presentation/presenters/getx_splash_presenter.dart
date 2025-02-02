import 'package:get/get.dart';

import '../../ui/pages/pages.dart';
import '../mixins/mixins.dart';

class GetxSplashPresenter extends GetxController with GetxNavigation implements SplashPresenter {
  GetxSplashPresenter();

  @override
  Future<void> init({int delayInSeconds = 2}) async {
    await Future.delayed(Duration(seconds: 2));
    // navigateTo = 'home';
  }
}
