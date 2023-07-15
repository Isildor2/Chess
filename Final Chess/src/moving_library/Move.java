package moving_library;

public record Move(int start_square, int target_square, int piece_type, int promotion_type,
					int en_passant_square, boolean move_is_capture) {}