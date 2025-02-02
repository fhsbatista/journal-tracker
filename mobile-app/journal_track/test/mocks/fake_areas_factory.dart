import 'package:journal_track/domain/entities/entities.dart';

class FakeAreasFactory {
  static List<Area> get entities {
    return [
      Area(id: 123, description: "health"),
      Area(id: 123, description: "diet"),
      Area(id: 124, description: "work"),
      Area(id: 125, description: "social life")
    ];
  }
}
