package test.testmaster.devtools;

import de.bentzin.tools.DevTools;
import de.bentzin.tools.task.Operation;
import de.bentzin.tools.time.Timing;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class test {

    public static void main(String[] args) {
        TabComplete tabComplete = new TabComplete(new List<List<Argument>>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NotNull
            @Override
            public Iterator<List<Argument>> iterator() {
                return null;
            }

            @NotNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NotNull
            @Override
            public <T> T[] toArray(@NotNull T[] a) {
                return null;
            }

            @Override
            public boolean add(List<Argument> arguments) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(@NotNull Collection<? extends List<Argument>> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, @NotNull Collection<? extends List<Argument>> c) {
                return false;
            }

            @Override
            public boolean removeAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(@NotNull Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public List<Argument> get(int index) {
                return null;
            }

            @Override
            public List<Argument> set(int index, List<Argument> element) {
                return null;
            }

            @Override
            public void add(int index, List<Argument> element) {

            }

            @Override
            public List<Argument> remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NotNull
            @Override
            public ListIterator<List<Argument>> listIterator() {
                return null;
            }

            @NotNull
            @Override
            public ListIterator<List<Argument>> listIterator(int index) {
                return null;
            }

            @NotNull
            @Override
            public List<List<Argument>> subList(int fromIndex, int toIndex) {
                return null;
            }
        }) {
            @Override
            public List<String> onTabComplete(CommandSender sender, @Nullable Command cmd, @NotNull String label, @Nullable @Nullable String[] args, List<Argument>[] arguments) {
                return null;
            }
        };
    }
}

