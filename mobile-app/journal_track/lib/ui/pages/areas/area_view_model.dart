import 'package:equatable/equatable.dart';

class AreaViewModel extends Equatable {
  final double id;
  final String description;

  const AreaViewModel({
    required this.id,
    required this.description,
  });


  @override
  List<Object?> get props => [id, description];

}