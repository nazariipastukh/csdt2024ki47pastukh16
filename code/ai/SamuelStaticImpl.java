package ai;

import ai.features.*;
import corelogic.CheckerBoard;
import corelogic.Piece;
import corelogic.Player;

public class SamuelStaticImpl
        extends ai.AIPlayer {
    ai.features.Feature[] features = new ai.features.Feature[]{new ai.features.DIFF(new double[]{0.8, 0.8, 0.95}), new ai.features.KCENT(new double[]{0.2, 0.85, 0.4}), new ai.features.MOB(new double[]{0.2, 0.2, 0.7}), new ai.features.ADV(new double[]{0.4, 0.3, 0.1})};

    public SamuelStaticImpl(CheckerBoard board) {
        super(board);
    }

    @Override
    double stateScore(CheckerBoard b, Player p) {
        double score = 0.0;
        int phase = this.phase(b);
        for (int i = 0; i < this.features.length; ++i) {
            score += this.features[i].getValue(b, p, phase);
        }
        return score;
    }

    private int phase(CheckerBoard b) {
        int count = 0;
        for (int y = 0; y < b.getBoardSize(); ++y) {
            for (int x = 0; x < b.getBoardSize(); ++x) {
                if (b.getLocation(x, y).getPiece() == Piece.Empty) continue;
                ++count;
            }
        }
        int boundary = (int) Math.floor(this.board.getSettings().getRuleSet().startingPieces * 2 / 3);
        int p = 3 - (int) Math.floor(count / boundary);
        if (p < 0) {
            p = 0;
        }
        if (p > 2) {
            p = 2;
        }
        return p;
    }
}