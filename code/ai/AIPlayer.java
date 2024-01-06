/**
 * @file AIPlayer.java
 * @brief Defines the abstract AIPlayer class.
 */

import corelogic.CheckerBoard;
import corelogic.Move;
import corelogic.Player;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * @class AIPlayer
 * @brief An abstract class representing an AI player in a game.
 *
 * This class provides a framework for implementing various AI players with different strategies.
 */
public abstract class AIPlayer implements Serializable {

    /** @brief The game board associated with the AI player. */
    CheckerBoard board;

    /** @brief A random number generator for random decisions. */
    private static Random r = new Random(System.currentTimeMillis());

    /** @brief The list of best moves found during the search. */
    LinkedList<Move> bestMove;

    /**
     * @brief Constructor for the AIPlayer class.
     * @param board The game board associated with the AI player.
     */
    public AIPlayer(CheckerBoard board) {
        this.board = board;
    }

    /**
     * @brief Gets the best move for the AI player.
     * @param board The current game board.
     * @return The best move determined by the AI player.
     */
    public Move getBestMove(CheckerBoard board) {
        return this.run(true);
    }

    /**
     * @brief Gets a hint move for the AI player.
     * @param board The current game board.
     * @return A hint move determined by the AI player.
     */
    public Move getHint(CheckerBoard board) {
        return this.run(false);
    }

    /**
     * @brief Executes the AI player's move selection logic.
     * @param isAI Indicates if the player is an AI (true) or human (false).
     * @return The selected move.
     */
    private Move run(boolean isAI) {
        this.bestMove = new LinkedList();
        this.getBestMoves(this.board, null, this.board.getSettings().getDifficulty().getValue(), -1.7976931348623157E308, Double.MAX_VALUE, true, isAI ? Player.Black : Player.White, Player.Black);
        return this.bestMove.get(r.nextInt(this.bestMove.size()));
    }

    /**
     * @brief Recursively searches for the best moves using minimax algorithm with alpha-beta pruning.
     * @param state The current game board state.
     * @param move The current move being considered.
     * @param depth The depth of the search tree.
     * @param alpha The alpha value for alpha-beta pruning.
     * @param beta The beta value for alpha-beta pruning.
     * @param maxPlayer Indicates if it's a maximizing player's turn.
     * @param currentP The current player making the move.
     * @param rootPlayer The original player that started the search.
     * @return The evaluation score for the current move.
     */
    private double getBestMoves(CheckerBoard state, Move move, int depth, double alpha, double beta, boolean maxPlayer, Player currentP, Player rootPlayer) {
        CheckerBoard newState;
        Move t;
        boolean isRoot = false;

        if (move != null) {
            newState = state.getDeepCopy();
            newState.applyMove(move);
        } else {
            newState = state;
            isRoot = true;
        }

        Player winner = newState.getWinner();

        if (winner != null) {
            if (winner == rootPlayer) {
                return 100.0;
            }
            if (winner != rootPlayer) {
                return -100.0;
            }
        }

        if (depth <= 0) {
            return this.stateScore(newState, currentP);
        }

        if (maxPlayer) {
            for (Move t2 : this.getLegalMovesFor(currentP, newState)) {
                double r = this.getBestMoves(newState, t2, depth - 1, alpha, beta, false, currentP == Player.Black ? Player.White : Player.Black, Player.Black);
                if (r >= alpha) {
                    if (isRoot) {
                        if (r > alpha) {
                            this.bestMove.clear();
                        }
                        this.bestMove.add(t2);
                    }
                    alpha = r;
                }
                if (beta <= alpha) break;
            }
            return alpha;
        }

        Iterator<Move> i$ = this.getLegalMovesFor(currentP, newState).iterator();
        while (i$.hasNext() && (beta = Math.min(beta, this.getBestMoves(newState, t = i$.next(), depth - 1, alpha, beta, true, currentP == Player.Black ? Player.White : Player.Black, Player.Black))) > alpha) {
        }
        return beta;
    }

    /**
     * @brief Gets the legal moves for a player on the given game board.
     * @param p The player for whom legal moves are being determined.
     * @param c The current game board.
     * @return A list of legal moves for the player.
     */
    private LinkedList<Move> getLegalMovesFor(Player p, CheckerBoard c) {
        LinkedList<Move> rtn = new LinkedList<Move>();
        for (int y = 0; y < c.getBoardSize(); ++y) {
            for (int x = 0; x < c.getBoardSize(); ++x) {
                for (Move t : c.getLegalMoves(p, x, y)) {
                    rtn.add(t);
                }
            }
        }
        return rtn;
    }

    /**
     * @brief Abstract method to be implemented by subclasses to provide a scoring mechanism for a game state.
     * @param state The current game board state.
     * @param player The player for whom the score is being calculated.
     * @return The score for the given game state.
     */
    abstract double stateScore(CheckerBoard state, Player player);

    /**
     * @enum Difficulty
     * @brief Enumeration representing difficulty levels for AI players.
     */
    public static enum Difficulty {
        Easy(2),
        Medium(4),
        Hard(6);

        /** @brief The ply value representing the difficulty level. */
        private int ply;

        /**
         * @brief Constructor for the Difficulty enum.
         * @param ply The ply value representing the difficulty level.
         */
        private Difficulty(int ply) {
            this.ply = ply;
        }

        /**
         * @brief Gets the ply value for the difficulty level.
         * @return The ply value.
         */
        public int getValue() {
            return this.ply;
        }
    }
}