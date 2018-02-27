from random import randrange


class State:
    def __init__(self, network, machine, infected):
        self.network = network
        self.machine = machine
        self.infected = infected

    def get_value(self):
        """Return the number of safe edges."""
        number_of_safe_edges = 0
        for machine in network:
            if machine not in self.infected:
                for linked_machine in network[machine]:
                    if linked_machine not in self.infected:
                        number_of_safe_edges += 1
        return number_of_safe_edges // 2

    def get_attacks(self):
        """Return the list of the infectable machines.

        :return: The list of uninfected machines linked to an infected machine
        """
        infectable_machines = []
        for machine in self.network:
            if machine in self.infected:
                infectable_machines.append(network[machine])
        return infectable_machines

    def get_defense(self):
        """Return the edges linked to an infected machine."""
        infected_edges = []
        for machine in self.network:
            if machine not in self.infected:
                for linked_machine in network[machine]:
                    if linked_machine not in self.infected:
                        infected_edges.append(network[machine])
        return infected_edges

    def play_attack(self, move):
        pass

    def play_defense(self, move):
        pass

    def is_finished(self):
        # return not obj.get_attack()
        pass


def generate_graph(nodes, number_of_edges):
    nodes = randrange(1, nodes)
    graph = {x: [] for x in range(1, nodes)}  # initialise nodes
    for x in graph:
        for y in range(randrange(1,
                                 number_of_edges + 1)):  # number of edges to append
            e = randrange(1, nodes)  # generate a random edge
            if e != x:
                graph[x].append(e)  # append the random edge
    return graph


def infect_initial_machine(network):
    return [1]  # return [randrange(1, len(network))]


if __name__ == "__main__":
    # network = generate_graph(10, 4)
    network = {1: [2, 5], 2: [1, 5, 4, 7], 3: [4, 6], 4: [5, 2, 3], 5: [4, 2,
                                                                        1], 6: [
        8, 7, 3], 7: [2, 6, 8], 8: [7, 6]}
    print(network)
    s = State(network, 1, infect_initial_machine(network))
    # for machine in network:
    #     print("Machine: " + str(machine) + ", edges: " + str(network[machine]))
    print(s.get_value())
    print(s.get_defense())
