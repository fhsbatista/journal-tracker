import 'package:flutter/material.dart';
import 'package:get/get.dart';

import './factories/factories.dart';

void main() {
  runApp(const App());
}

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    final routeObserver = Get.put<RouteObserver>(RouteObserver<PageRoute>());
    return GetMaterialApp(
      title: 'Journal Track',
      debugShowCheckedModeBanner: false,
      initialRoute: "/",
      getPages: [
        GetPage(
          name: '/',
          page: makeSplashPage,
          transition: Transition.fade,
        ),
        GetPage(
          name: '/areas',
          page: makeAreasPage,
          transition: Transition.fadeIn,
        )
      ],
    );
  }
}
