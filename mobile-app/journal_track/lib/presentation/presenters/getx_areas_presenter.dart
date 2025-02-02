import 'dart:ffi';

import 'package:get/get.dart';
import 'package:journal_track/presentation/mixins/mixins.dart';

import '../../domain/usecases/usecases.dart';
import '../../ui/pages/areas/areas.dart';

class GetxAreasPresenter extends GetxController
  with GetxLoading, GetxNavigation
  implements AreasPresenter {

    final LoadAreas loadAreas;

    GetxAreasPresenter({required this.loadAreas});
  
  @override
  Stream<List<AreaViewModel>> get areasStream => throw UnimplementedError();
  
  @override
  Future<void> loadData() async {
    loadAreas.load();
  }
  
}
