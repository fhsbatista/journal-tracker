import 'dart:ffi';

import 'package:get/get.dart';
import 'package:journal_track/presentation/mixins/mixins.dart';

import '../../domain/helpers/helpers.dart';
import '../../domain/usecases/usecases.dart';
import '../../ui/errors/errors.dart';
import '../../ui/pages/areas/areas.dart';

class GetxAreasPresenter extends GetxController
    with GetxLoading, GetxNavigation
    implements AreasPresenter {
  final LoadAreas loadAreas;

  final _areas = Rx<List<AreaViewModel>>([]);

  @override
  Stream<List<AreaViewModel>> get areasStream => _areas.stream;

  GetxAreasPresenter({required this.loadAreas});

  @override
  Future<void> loadData() async {
    try {
      isLoading = true;
      final areas = await loadAreas.load();
      _areas.value = areas
          .map((e) => AreaViewModel(id: e.id, description: e.description))
          .toList();
    } on DomainError catch (error) {
      _areas.addError(UiError.unexpected.description, StackTrace.empty);
    } finally {
      isLoading = false;
    }
  }
}
