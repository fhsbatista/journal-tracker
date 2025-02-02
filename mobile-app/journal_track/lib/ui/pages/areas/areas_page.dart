import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:get/get.dart';
import 'package:journal_track/ui/pages/areas/areas.dart';
import 'package:provider/provider.dart';

import '../../components/components.dart';
import '../../helpers/i18n/i18n.dart';
import '../../mixins/mixins.dart';
import 'components/components.dart';

class AreasPage extends StatefulWidget {
  const AreasPage(this.presenter, {super.key});

  final AreasPresenter presenter;

  @override
  State<AreasPage> createState() => _AreasPageState();
}

class _AreasPageState extends State<AreasPage>
    with Loading, Navigation, RouteAware {
  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addPostFrameCallback((_) {
      widget.presenter.loadData();
    });
  }

  @override
  void didPopNext() {
    super.didPopNext();
    widget.presenter.loadData();
  }

  @override
  void dispose() {
    Get.find<RouteObserver>().unsubscribe(this);
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    Get.find<RouteObserver>().subscribe(
      this,
      ModalRoute.of(context) as PageRoute,
    );

    return Scaffold(
      appBar: AppBar(title: Text(I18n.strings.areas)),
      body: Builder(builder: (context) {
        handleLoading(context, widget.presenter.isLoadingStream);
        handleNavigation(widget.presenter.navigateToStream);
        return StreamBuilder<List<AreaViewModel>>(
          stream: widget.presenter.areasStream,
          builder: (context, snapshot) {
            if (snapshot.hasError) {
              return Center(child: ReloadScreen(
                  error: snapshot.error.toString(),
onReloadClick: widget.presenter.loadData,
              ),);
            }

            if (snapshot.hasData) {
              return ListenableProvider(
                create: (_) => widget.presenter,
                child: AreaItemsList(snapshot.data ?? []),
              );
            }

            return SizedBox.shrink();
          }
        );
      })
    );
  }
}
