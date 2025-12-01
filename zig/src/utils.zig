const std = @import("std");

pub fn lines(allocator: std.mem.Allocator, input: []const u8) !std.ArrayList([]const u8) {
    var result = try std.ArrayList([]const u8).initCapacity(allocator, 0);
    var iter = std.mem.splitScalar(u8, input, '\n');
    while (iter.next()) |line| {
        try result.append(allocator, line);
    }
    return result;
}
