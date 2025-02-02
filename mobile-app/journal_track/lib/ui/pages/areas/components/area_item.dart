import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../areas.dart';

class AreaItem extends StatelessWidget {
  final AreaViewModel viewModel;

  const AreaItem(this.viewModel);

  @override
  Widget build(BuildContext context) {
    final presenter = Provider.of<AreasPresenter>(context);
    return InkWell(
      onTap: () => {},
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 5),
        child: Card(
          margin: EdgeInsets.all(16.0),
          child: Container(
              padding: const EdgeInsets.all(8.0),
              width: double.infinity,
              child: Text(viewModel.description)),
        ),
      ),
    );
  }
}
