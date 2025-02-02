import 'package:journal_track/domain/entities/area.dart';

import '../../../domain/usecases/usecases.dart';

class RemoteLoadAreas implements LoadAreas {
  RemoteLoadAreas();

  @override
  Future<List<Area>> load() async {
    return [
      Area(id: 123, description: 'Health'),
      Area(id: 123, description: 'Wealth'),
      Area(id: 123, description: 'Diet'),
      Area(id: 123, description: 'Work'),
    ];
  }


}