import 'package:flutter_test/flutter_test.dart';
import 'package:journal_track/domain/entities/entities.dart';
import 'package:journal_track/domain/helpers/helpers.dart';
import 'package:journal_track/domain/usecases/usecases.dart';
import 'package:journal_track/presentation/presenters/getx_areas_presenter.dart';
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
}
