package net.zepalesque.redux.api.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.mojang.serialization.Codec;

import java.util.Optional;
import java.util.function.Function;

/** Essentially a simpler form of a {@link Codec} */
public interface Mapper<T> {

    /** Deserialize a value from a {@link JsonElement} */
    Optional<T> decode(JsonElement data);

    /** Serialize a value to a {@link JsonElement} */
    JsonElement encode(T value);

    /** Deserialize a value from a {@link String} */
    Optional<T> read(String data);

    /** Serialize a value to a {@link String} */
    String write(T value);

    /** Returns a mapper which converts a value with a given function. See {@link com.mojang.serialization.Codec#xmap(Function, Function)} */
    default <Q> Mapper<Q> xmap(Function<T, Q> to, Function<Q, T> from) {
        Function<Optional<T>, Optional<Q>> optionalFunc = ogVal -> ogVal.isEmpty() ? Optional.empty() : Optional.of(to.apply(ogVal.get()));
        Mapper<T> og = this;
        return new Mapper<>() {
            @Override
            public Optional<Q> decode(JsonElement json) {
                return optionalFunc.apply(og.decode(json));
            }

            @Override
            public JsonElement encode(Q q) {
                return og.encode(from.apply(q));
            }

            @Override
            public Optional<Q> read(String s) {
                return optionalFunc.apply(og.read(s));
            }

            @Override
            public String write(Q q) {
                return og.write(from.apply(q));
            }

        };
    }

    /** Returns a mapper designed to function with a given Enum. } */
    static <A extends Enum<A>> Mapper<A> fromEnum(Class<A> clazz) {
        return new Mapper<>() {
            @Override
            public Optional<A> decode(JsonElement json) {
                try {
                    return json.isJsonPrimitive() ?
                            Optional.of(Enum.valueOf(clazz,
                                    json.getAsString())) : Optional.empty();
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }

            @Override
            public JsonElement encode(A a) {
                return new JsonPrimitive(a.name());
            }

            @Override
            public Optional<A> read(String s) {
                try {
                    return Optional.of(Enum.valueOf(clazz, s));
                } catch (Exception e) {
                    e.printStackTrace();
                    return Optional.empty();
                }
            }

            @Override
            public String write(A a) {
                return a.name();
            }
        };
    }
}
