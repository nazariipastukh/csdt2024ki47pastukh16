package corelogic;

import java.io.Serializable;

public class RuleSet
        implements Serializable {

    public static enum RuleSets {
        English(corelogic.Player.Black, 8, false, false, true, corelogic.MustLandKings.TurnStop, 12),
        International(corelogic.Player.White, 10, true, true, true, corelogic.MustLandKings.True, 20);

        public corelogic.Player firstMove;
        public int boardSize;
        public boolean menCaptureBackwards;
        public boolean longRangeKings;
        public boolean mustCapture;
        public corelogic.MustLandKings mustLandKings;
        public int startingPieces;

        private RuleSets(corelogic.Player firstMove, int boardSize, boolean menCaptureBackwards, boolean longRangeKings, boolean mustCapture, corelogic.MustLandKings mustLandKings, int startingPieces) {
            this.firstMove = firstMove;
            this.boardSize = boardSize;
            this.menCaptureBackwards = menCaptureBackwards;
            this.longRangeKings = longRangeKings;
            this.mustCapture = mustCapture;
            this.mustLandKings = mustLandKings;
            this.startingPieces = startingPieces;
        }

        public corelogic.Player getFirstMove() {
            return this.firstMove;
        }

        public int getBoardSize() {
            return this.boardSize;
        }

        public boolean menCaptureBackwards() {
            return this.menCaptureBackwards;
        }

        public boolean longRangeKings() {
            return this.longRangeKings;
        }

        public boolean mustCapture() {
            return this.mustCapture;
        }

        public corelogic.MustLandKings mustLandKings() {
            return this.mustLandKings;
        }

        public int startingPieces() {
            return this.startingPieces;
        }
    }

}
