package state.game;

import java.util.Optional;

public enum ChallengeDifficulty {
    Fast,
    Medium,
    Long,
    Random;

    public static Optional<ChallengeDifficulty> fromString(String value) {
        if (value == null) {
            return Optional.empty();
        }

        for (ChallengeDifficulty difficulty : ChallengeDifficulty.values()) {
            if (difficulty.name().equalsIgnoreCase(value)) {
                return Optional.of(difficulty);
            }
        }

        return Optional.empty();
    }
}
