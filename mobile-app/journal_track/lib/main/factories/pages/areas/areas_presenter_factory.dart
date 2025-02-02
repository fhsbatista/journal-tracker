import '../../../../ui/pages/pages.dart';
import '../../../../presentation/presenters/presenters.dart';
import '../../factories.dart';

AreasPresenter makeGetxAreasPresenter() {
  return GetxAreasPresenter(
    loadAreas: makeLoadAreas(),
  );
}
