package com.leaderboard.api.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Badge {
	CODING_NINJA("CODING_NINJA"),
	CODE_CHAMP("CODING_CHAMP"),
	CODE_MASTER("CODE_MASTER");
	
	private final String value;

    public static Badge get(final String name) {
        return Stream.of(Badge.values())
            .filter(p -> p.name().equals(name.toUpperCase()) || p.getValue().equals(name.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(String.format("Invalid badge name: %s", name)));
    }
}
