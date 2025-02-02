import '../helpers/i18n/i18n.dart';

enum UiError {
  unexpected,
}

extension UiErrorExtensions on UiError {
  String get description {
    final dict = {
      UiError.unexpected: I18n.strings.msgUnexpectedError,
    };

    return dict[this] ?? I18n.strings.msgUnexpectedError;
  }
}