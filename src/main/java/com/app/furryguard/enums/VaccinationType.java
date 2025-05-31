package com.app.furryguard.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum VaccinationType {
    D("Чума плотоядных"),
    H("Инфекционный гепатит"),
    P("Паровирусный энтерит"),
    Pi("Парагрипп"),
    L("Лептоспироз"),
    R("Бещенство");

    private final String description;

}
