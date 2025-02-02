import '../entities/entities.dart';

abstract class LoadAreas {
  Future<List<Area>> load();
}