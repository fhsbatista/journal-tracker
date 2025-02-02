import 'package:flutter/material.dart';
import 'package:journal_track/ui/pages/areas/components/components.dart';

import '../areas.dart';

class AreaItemsList extends StatelessWidget {
  final List<AreaViewModel> areas;

  const AreaItemsList(this.areas);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 20),
      child: ListView(
        children: areas.map((viewModel) => AreaItem(viewModel)).toList(),
      ),
    );
  }
}