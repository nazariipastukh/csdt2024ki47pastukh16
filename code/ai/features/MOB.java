package ai.features;

import corelogic.CheckerBoard;
import corelogic.Piece;
import corelogic.Player;

import java.io.Serializable;

public class MOB
        implements ai.features.Feature,
        Serializable {
    double[] coeff;

    public MOB(double[] coeff) {
        this.coeff = coeff;
    }

    @Override
    public double getValue(CheckerBoard b, Player active, int phase) {
        double score = 0.0;
        for (int y = 0; y < b.getBoardSize(); ++y) {
            for (int x = 0; x < b.getBoardSize(); ++x) {
                if (b.getLocation(x, y).getPlayer() != active || b.getLocation(x, y).getPiece() == Piece.Empty)
                    continue;
                score += (double) b.getLegalMoves(active, x, y).size();
            }
        }
        return score * this.coeff[phase];
    }
}