import 'package:flutter/foundation.dart';

import 'areas.dart';

abstract class AreasPresenter implements Listenable {
  Stream<bool> get isLoadingStream;
  Stream<List<AreaViewModel>> get areasStream;
  Stream<String?> get navigateToStream;

  void loadData();
}