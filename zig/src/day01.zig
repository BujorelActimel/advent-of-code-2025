const std = @import("std");
const utils = @import("utils.zig");

pub fn part1(allocator: std.mem.Allocator, input: []const u8) ![]const u8 {
    var password: i32 = 0;
    var dialPosition: i32 = 50;
    var rotations = try utils.lines(allocator, input);
    defer rotations.deinit(allocator);

    for (rotations.items) |rotation| {
        const direction = rotation[0];
        const distance = @mod(try std.fmt.parseInt(i32, rotation[1..], 10), 100);

        if (direction == 'L') {
            dialPosition -= distance;
        } else {
            dialPosition += distance;
        }

        dialPosition = @mod(dialPosition, 100);

        if (dialPosition == 0) {
            password += 1;
        }
    }

    return try std.fmt.allocPrint(allocator, "{d}", .{password});
}

pub fn part2(allocator: std.mem.Allocator, input: []const u8) ![]const u8 {
    var password: i32 = 0;
    var dialPosition: i32 = 50;
    var rotations = try utils.lines(allocator, input);
    defer rotations.deinit(allocator);

    for (rotations.items) |rotation| {
        const direction = rotation[0];
        var distance = try std.fmt.parseInt(i32, rotation[1..], 10);

        password += @divFloor(distance, 100);
        distance = @mod(distance, 100);

        if (direction == 'L') {
            if (dialPosition - distance <= 0 and dialPosition != 0) {
                password += 1;
            }
            dialPosition = @mod(dialPosition - distance, 100);
        } else {
            if (dialPosition + distance >= 100 and dialPosition != 0) {
                password += 1;
            }
            dialPosition = @mod(dialPosition + distance, 100);
        }
    }

    return try std.fmt.allocPrint(allocator, "{d}", .{password});
}
