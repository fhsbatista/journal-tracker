import 'package:flutter_test/flutter_test.dart';
import 'package:journal_track/domain/entities/entities.dart';
import 'package:journal_track/domain/helpers/helpers.dart';
import 'package:journal_track/domain/usecases/usecases.dart';
import 'package:journal_track/presentation/presenters/getx_areas_presenter.dart';
import 'package:journal_track/ui/pages/areas/areas.dart';
import 'package:mocktail/mocktail.dart';

import '../../mocks/mocks.dart';
import 'getx_areas_presenter_test.mocks.dart';

void main() {
  late GetxAreasPresenter sut;
  late MockLoadAreas loadAreas;
  late List<Area> areas;

  When mockAreasCall() => when(loadAreas.load);

  void mockLoadAreas(List<Area> data) async =>
      mockAreasCall().thenAnswer((_) async => data);

  void mockLoadAreasError() =>
      mockAreasCall().thenThrow(DomainError.unexpected);

  setUp(() {
    loadAreas = MockLoadAreas();
    sut = GetxAreasPresenter(loadAreas: loadAreas);
    areas = FakeAreasFactory.entities;
    mockLoadAreas(areas);
  });

  test('Should call LoadAreas on loadData', () async {
    await sut.loadData();

    verify(loadAreas.load).called(1);
  });

  test('Should emit correct events on LoadAreas success', () async {
    expectLater(sut.isLoadingStream, emitsInOrder([true, false]));
    sut.areasStream.listen(expectAsync1(
      (areas) => expect(areas, [
        AreaViewModel(id: areas[0].id, description: areas[0].description),
        AreaViewModel(id: areas[1].id, description: areas[1].description),
        AreaViewModel(id: areas[2].id, description: areas[2].description),
        AreaViewModel(id: areas[3].id, description: areas[3].description),
      ]),
    ));

    await sut.loadData();
  }); 
}
