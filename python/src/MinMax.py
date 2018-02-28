from math import inf


class MinMax:
    def minmax(self, state, depth):
        if not depth or state.is_finished():
            return state.get_value(), None
        if state.player == "defender":
            max, coup = -inf, None
            for c2 in state.get_defense():
                value = self.minmax(state.play_defense(c2), depth - 1)
                if value > max:
                    max = value
                    coup = c2
                return max, coup
        if state.player == "attacker":
            min, coup = inf, None
            for c2 in state.get_attacks():
                value = self.minmax(state.play_attack(c2), depth - 1)
                if value < min:
                    min = value
                    coup = c2
                return min, coup
