from math import inf


class MinMax:
    def minmax(self, s, d):
        if not d or s.is_finished():
            return s.get_value(), None
        if s.player == "defender":
            m, c = -inf, None
            for c2 in s.get_defense():
                v = self.minmax(s.play_defense(c2), d - 1)
                if v > m:
                    m = v
                    c = c2
                return m, c
        if s.player == "attacker":
            m, c = inf, None
            for c2 in s.get_attack():
                v = self.minmax(s.play_attack(c2), d - 1)
                if v < m:
                    m = v
                    c = c2
                return m, c
