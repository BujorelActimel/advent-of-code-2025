const std = @import("std");
const day01 = @import("day01.zig");
const day02 = @import("day02.zig");
const day03 = @import("day03.zig");
const day04 = @import("day04.zig");
const day05 = @import("day05.zig");
const day06 = @import("day06.zig");
const day07 = @import("day07.zig");
const day08 = @import("day08.zig");
const day09 = @import("day09.zig");
const day10 = @import("day10.zig");
const day11 = @import("day11.zig");
const day12 = @import("day12.zig");

pub fn main() !void {
    // args are validated by the aoc script

    var gpa = std.heap.GeneralPurposeAllocator(.{}){};
    defer _ = gpa.deinit();
    const allocator = gpa.allocator();

    const args = try std.process.argsAlloc(allocator);
    defer std.process.argsFree(allocator, args);

    const day = args[1];
    const part = args[2];

    const input_path = try std.fmt.allocPrint(allocator, "../inputs/day{s}-{s}.txt", .{ day, part });
    defer allocator.free(input_path);

    const input_file = try std.fs.cwd().openFile(input_path, .{});
    defer input_file.close();

    const input = try input_file.readToEndAlloc(allocator, 1024 * 1024);
    defer allocator.free(input);

    const result = if (std.mem.eql(u8, day, "01"))
        if (std.mem.eql(u8, part, "1")) try day01.part1(allocator, input) else try day01.part2(allocator, input)
    else if (std.mem.eql(u8, day, "02"))
        if (std.mem.eql(u8, part, "1")) try day02.part1(allocator, input) else try day02.part2(allocator, input)
    else if (std.mem.eql(u8, day, "03"))
        if (std.mem.eql(u8, part, "1")) try day03.part1(allocator, input) else try day03.part2(allocator, input)
    else if (std.mem.eql(u8, day, "04"))
        if (std.mem.eql(u8, part, "1")) try day04.part1(allocator, input) else try day04.part2(allocator, input)
    else if (std.mem.eql(u8, day, "05"))
        if (std.mem.eql(u8, part, "1")) try day05.part1(allocator, input) else try day05.part2(allocator, input)
    else if (std.mem.eql(u8, day, "06"))
        if (std.mem.eql(u8, part, "1")) try day06.part1(allocator, input) else try day06.part2(allocator, input)
    else if (std.mem.eql(u8, day, "07"))
        if (std.mem.eql(u8, part, "1")) try day07.part1(allocator, input) else try day07.part2(allocator, input)
    else if (std.mem.eql(u8, day, "08"))
        if (std.mem.eql(u8, part, "1")) try day08.part1(allocator, input) else try day08.part2(allocator, input)
    else if (std.mem.eql(u8, day, "09"))
        if (std.mem.eql(u8, part, "1")) try day09.part1(allocator, input) else try day09.part2(allocator, input)
    else if (std.mem.eql(u8, day, "10"))
        if (std.mem.eql(u8, part, "1")) try day10.part1(allocator, input) else try day10.part2(allocator, input)
    else if (std.mem.eql(u8, day, "11"))
        if (std.mem.eql(u8, part, "1")) try day11.part1(allocator, input) else try day11.part2(allocator, input)
    else if (std.mem.eql(u8, day, "12"))
        if (std.mem.eql(u8, part, "1")) try day12.part1(allocator, input) else try day12.part2(allocator, input)
    else { // unreachable because script validation, but compiler complains
        std.debug.print("Unknown day: {s}\n", .{day});
        return;
    };

    defer allocator.free(result);
    std.debug.print("{s}\n", .{result});
}
