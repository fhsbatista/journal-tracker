import 'package:equatable/equatable.dart';

class Area extends Equatable {
  final double id;
  final String description;

  const Area({
    required this.id,
    required this.description,
  });
  
  @override
  List<Object?> get props => [id, description];

}