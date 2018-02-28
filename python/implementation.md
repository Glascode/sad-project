`State` class
=============

| class State                |
|----------------------------|
| - graph (network)          |
| - infected machines        |
| - current player           |
| -------------------------- |
| + getValue(): int          |
| + getAttacks(): moves_list |
| + getDefense(): moves_list |
| + playAttack(coup): State  |
| + playDefense(coup): State |
| + isFinished(): bool       |


For `get_defense()`:
- module: `itertools` (power set)

Get attacks
=============
The list of all the uninfected machines linked to an infected machine

Get defense
=============
The list of all the edges of uninfected machines linked to an infected machine

Running the game
================
```Python
s = State()
while not s.is_finished():
    coup = minmax(s, d_defense())
    s = s.play_defense()
    coup = minmax(s, d_attack())
    s = s.play_attack(coup)
```

`Minmax` implementation
=====================

```Python
from math import inf

def minmax(self, s, d, alpha, beta):
    if not d or s.is_finished():
        return s.get_value(), None
    if s.player == "defender":
        m, c = -inf, None
        for c2 in s.get_defense():
            v = self.minmax(s.play_defense(c2), d - 1, alpha, beta)
            if v > alpha:
                alpha = v
            if v > m:
                m = v
                c = c2
            return m, c
    if s.player == "attacker":
        m, c = inf, None
        for c2 in s.get_attack():
            v = self.minmax(s.play_attack(c2), d - 1)
            if v < beta:
                beta = v
            if v < m:
                m = v
                c = c2
            return m, c
```

where `d` is the depth of the tree of moves.
