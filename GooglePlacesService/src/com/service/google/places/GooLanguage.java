package com.service.google.places;

public enum GooLanguage {

	ARABIC("ar"), BULGARIAN("bg"), BENGALI("bn"), CATALAN("ca"), CZECH("cs"), DANISH(
			"da"), GERMAN("de"), GREEK("el"), ENGLISH("en"), SPANISH("es"), FINNISH(
			"fi"), FILIPINO("fil"), FRENCH("fr"), GALICIAN("gl"), GUJARATI("gu"), HINDI(
			"hi"), CROATIAN("hr"), HUNGARIAN("hu"), INDONESIAN("id"), ITALIAN(
			"it"), HEBREW("iw"), JAPANESE("ja"), KANNADA("kn"), KOREAN("ko"), LITHUANIAN(
			"lt"), LATVIAN("lv"), MALAYALAM("ml"), MARATHI("mr"), DUTCH("nl"), NORWEGIAN(
			"no"), POLISH("pl"), PORTUGUESE("pt"), PORTUGUESE_BRASIL("pt-BR"), PORTUGUESE_PORTUGAL(
			"pt-PT"), ROMANIAN("ro"), RUSSIAN("ru"), SLOVAK("sk"), SLOVENIAN(
			"sl"), SERBIAN("sr"), SWEDISH("sv"), TAMIL("ta"), TELUGU("te"), THAI(
			"th"), TURKISH("tr"), UKRAINIAN("uk"), VIETNAMESE("vi"), CHINESE_SIMPLIFIED(
			"zh-CN"), CHINESE_TRADITIONAL("zh-TW"), ORIYA_V2("or"), ROMANSCH_V2(
			"rm"), NORWEGIAN_NYNORSK_V2("nn"), BASQUE_V3("eu"), ENGLISH_AUSTRALIAN_V3(
			"en-AU"), ENGLISH_GREAT_BRITAIN_V3("en-GB"), FARSI_V3("fa"), TAGALOG_V3(
			"tl");

	private String code;

	private GooLanguage(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
